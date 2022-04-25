package towerDefence.enemies;

import towerDefence.components.Animation;
import towerDefence.components.Collision;
import towerDefence.components.damage.Damage;
import towerDefence.components.debuff.DebuffManager;
import towerDefence.components.debuff.IDebuff;
import towerDefence.components.movement.SplineMovement;
import towerDefence.view.sprite.Sprite;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy implements IEnemy {

    private int health;
    private Damage damageOverTime;

    private SplineMovement splineMovement;
    private double pathOffset;

    private int zDepth;


    /** Sprite engine driving the sprite graphics and animations
     * of the enemy.
     */
    private SpriteEngine spriteEngine;
    private boolean isDead = false;

    /**
     * Collision circle of the enemy. Tower and projectiles
     * will scan for collision against this.
     */
    private Collision collision;

    private EnemyState enemyState;

    private DebuffManager debuffManager;

    private int moneyLoot;

    // Animations
    private Animation animMoveRight;
    private Animation animMoveLeft;
    private Animation animDeath;

    public Enemy(int health, SplineMovement splineMovement,
                 SpriteEngine spriteEngine, Collision collision,
                 Animation animMoveRight, Animation animMoveLeft, Animation animDeath, int moneyLoot) {
        this.health = health;
        this.splineMovement = splineMovement;
        this.spriteEngine = spriteEngine;
        this.collision = collision;

        this.animMoveRight = animMoveRight;
        this.animMoveLeft = animMoveLeft;
        this.animDeath = animDeath;

        this.moneyLoot = moneyLoot;

        debuffManager = new DebuffManager(this);
        pathOffset = generateRandomOffset();
    }

    // For testing
    public Enemy() {
    }

    private double generateRandomOffset(){
        double maxOffset = 0.0;
        Random rand = new Random();
        return maxOffset * (rand.nextDouble() * 2 - 1);
    }



    @Override
    public void update(double deltaSteps) {
        splineMovement.update(deltaSteps);
        debuffManager.update(deltaSteps);

        Point2D pos = getPosition();
        collision.setPosition(new Point2D.Double(
                pos.getX() + getSprite().width / 2.0,
                pos.getY() + collision.getRadius() / 2.0));

        if (splineMovement.movementDone()) {
            death();
        }


        // Update movement direction
        if (enemyState != EnemyState.DYING) {
            enemyState = updateMovementDirection();
        }

        updateAnimation(deltaSteps);
    }

    private EnemyState updateMovementDirection() {
        if (checkIfMovingRight(splineMovement.getRotation())) {
            return EnemyState.MOVING_RIGHT;
        } else {
            return EnemyState.MOVING_LEFT;
        }
    }

    private void updateAnimation(double deltaSteps){
        switch (enemyState) {
            case MOVING_RIGHT -> playAnimation(animMoveRight);
            case MOVING_LEFT -> playAnimation(animMoveLeft);
            case DYING -> playAnimation(animDeath);
        }
        spriteEngine.update(deltaSteps);
    }

    private void playAnimation(Animation anim) {
        spriteEngine.start(anim);
    }

    private boolean checkIfMovingRight(Double angle) {
        return angle < Math.PI / 2.0;
    }

    @Override
    public Point2D getPosition() {
        Point2D position = splineMovement.getPosition();
        double offsetX = getSprite().width / 2.0;
        double offsetY = getSprite().height;

        Point2D normal = splineMovement.getUnitNormalVector();

        double x = position.getX() + normal.getX() * pathOffset - offsetX;
        double y = position.getY() - offsetY + normal.getY() * pathOffset;

        return new Point2D.Double(x, y);
    }

    @Override
    public Collision getCollision() {
        return collision;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void applyDebuff(IDebuff debuff) {
        debuffManager.addDebuff(debuff);
    }

    @Override
    public SplineMovement getMovement() {
        return splineMovement;
    }

    @Override
    public DebuffManager getDebuffManager() {
        return debuffManager;
    }

    @Override
    public int getMoneyLoot() {
        return moneyLoot;
    }

    @Override
    public double getPathProgression() {
        return splineMovement.getPathProgression();
    }

    private void death() {
        isDead = true;
    }

    @Override
    public int getZDepth() {
        return (int) getPosition().getY();
    }

    @Override
    public Sprite getSprite(){
        return spriteEngine.getSprite();
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void applyDamage(Damage damage) {
        health = damage.applyDamage(health);
        if (health <= 0) {
            death();
        }
    }
}
