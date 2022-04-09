package towerDefence.enemies;

import towerDefence.components.Animation;
import towerDefence.components.CollisionObject;
import towerDefence.components.Collision;
import towerDefence.components.damage.Damage;
import towerDefence.components.damage.DamageOverTime;
import towerDefence.components.damage.IDamageable;
import towerDefence.components.movement.SplineMovement;
import towerDefence.view.IRenderableObject;
import towerDefence.view.sprite.Sprite;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;
import java.util.Random;

public class Enemy implements IEnemy, IDamageable, CollisionObject {

    private int health;
    private DamageOverTime damageOverTime;

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

    // Animations
    private Animation animMoveRight;
    private Animation animMoveLeft;
    private Animation animDeath;

    public Enemy(int health, SplineMovement splineMovement,
                 SpriteEngine spriteEngine, Collision collision,
                 Animation animMoveRight, Animation animMoveLeft, Animation animDeath) {
        this.health = health;
        this.splineMovement = splineMovement;
        this.spriteEngine = spriteEngine;
        this.collision = collision;

        this.animMoveRight = animMoveRight;
        this.animMoveLeft = animMoveLeft;
        this.animDeath = animDeath;

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
        collision.setPosition(getPosition());

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
        double offsetY = spriteEngine.getSprite().height / 2.0;

        Point2D normal = splineMovement.getUnitNormalVector();

        double x = position.getX() + normal.getX() * pathOffset;
        double y = position.getY() - offsetY + normal.getY() * pathOffset;

        return new Point2D.Double(x, y);
    }

    @Override
    public Collision getCollision() {
        return collision;
    }

    @Override
    public void applyDamageOverTime(Damage damage) {

    }

    @Override
    public void applyDamage(int damageValue) {
        health -= damageValue;
        if (health < 0) {
            death();
        }
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
}
