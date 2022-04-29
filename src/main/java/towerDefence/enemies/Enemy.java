package towerDefence.enemies;

import towerDefence.view.sprite.Animation;
import towerDefence.components.collision.Collision;
import towerDefence.components.damage.Damage;
import towerDefence.components.debuff.DebuffManager;
import towerDefence.components.debuff.IDebuff;
import towerDefence.components.movement.SplineMovement;
import towerDefence.view.sprite.Sprite;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;
import java.util.Random;

public abstract class Enemy implements IEnemy {

    private int health;

    private SplineMovement splineMovement;

    /**
     * Offset position from path center
     */
    private double pathOffset;

    /** Sprite engine driving the sprite graphics and animations
     * of the enemy.
     */
    private SpriteEngine spriteEngine;

    /**
     * True if movement is done and enemy reached the end.
     */
    private boolean reachedEnd = false;

    /**
     * True if enemy is dead and should be removed.
     */
    private boolean isDead = false;

    /**
     * Collision circle of the enemy. Tower and projectiles
     * will scan for collision against this.
     */
    private Collision collision;


    private EnemyState enemyAnimationState;

    /**
     * Manages all debuffs on enemy. Includes damage over time and slow effects
     */
    private DebuffManager debuffManager;

    /**
     * Money earned by killing this enemy
     */
    private int moneyLoot;

    /**
     * Damage done to player if enemy reaches the end of the path
     */
    private int reachedEndDamage;

    /**
     * Progression the path.
     */
    private double progression = 0.0;

    /**
     * Z-depth value of the enemy. Determines the rendering order.
     * Based of the y-coordinate of the enemy.
     */
    private int zDepth = 0;

    // Animations
    private Animation animMoveRight;
    private Animation animMoveLeft;
    private Animation animDeath;

    public Enemy(int health, SplineMovement splineMovement,
                 SpriteEngine spriteEngine, Collision collision,
                 Animation animMoveRight, Animation animMoveLeft, Animation animDeath, int moneyLoot, int reachedEndDamage) {
        this.health = health;
        this.splineMovement = splineMovement;
        this.spriteEngine = spriteEngine;
        this.collision = collision;

        this.animMoveRight = animMoveRight;
        this.animMoveLeft = animMoveLeft;
        this.animDeath = animDeath;

        this.moneyLoot = moneyLoot;
        this.reachedEndDamage = reachedEndDamage;

        debuffManager = new DebuffManager(this);
        pathOffset = generateRandomOffset();
    }

    // For testing
    protected Enemy(double progression) {
        this.progression = progression;
    }

    protected Enemy() {
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
                splineMovement.getPosition().getX(),
                splineMovement.getPosition().getY() - getSprite().height / 2.0
        ));

        if (splineMovement.movementDone()) {
            reachedEnd();
        }

        // Update movement direction
        if (enemyAnimationState != EnemyState.DYING) {
            enemyAnimationState = updateMovementDirection();
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
        switch (enemyAnimationState) {
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

        zDepth = (int) y;

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
        if (splineMovement != null) {
            progression = splineMovement.getPathProgression();
        }
        return progression;
    }

    private void death() {
        isDead = true;
    }

    private void reachedEnd() {
        reachedEnd = true;
        death();
    }

    @Override
    public boolean hasReachedEnd() {
        return reachedEnd;
    }

    @Override
    public int getReachedEndDamage() {
        return reachedEndDamage;
    }

    @Override
    public int getZDepth() {
        return zDepth;
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
