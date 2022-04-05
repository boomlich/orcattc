package towerDefence.enemies;

import towerDefence.components.damage.Damage;
import towerDefence.components.damage.DamageOverTime;
import towerDefence.components.damage.IDamage;
import towerDefence.components.damage.IDamageable;
import towerDefence.components.movement.SplineMovement;
import towerDefence.model.GameEntities;
import towerDefence.view.sprite.Sprite;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;

public class Enemy implements IEnemy, IDamageable {

    private int health;
    private DamageOverTime damageOverTime;
    private SplineMovement splineMovement;
    private SpriteEngine spriteEngine;
    private boolean isDead;

    public Enemy(int health, SplineMovement splineMovement, SpriteEngine spriteEngine) {
        this.health = health;
        this.splineMovement = splineMovement;
        this.spriteEngine = spriteEngine;
        isDead = false;
    }

    // For testing
    public Enemy() {
    }

    @Override
    public void update(double deltaSteps) {
        splineMovement.update(deltaSteps);
        spriteEngine.setSpriteRotation(splineMovement.getRotation());

        if (splineMovement.movementDone()) {
            death();
        }
    }

    @Override
    public Point2D getPosition() {
        return splineMovement.getPosition();
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
    public Sprite getSprite(){
        return spriteEngine.getSprite();
    }

    @Override
    public boolean isDead() {
        return isDead;
    }
}
