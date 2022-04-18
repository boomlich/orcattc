package towerDefence.components;

import towerDefence.components.damage.Damage;
import towerDefence.components.damage.IDamageable;
import towerDefence.components.movement.IMovement;
import towerDefence.components.movement.LinearMovement;
import towerDefence.enemies.IEnemy;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Projectile implements IDamageable {

    private int health;
    private Damage damage;
    private Damage penetrationDamage = new Damage(200);
    private double speed;
    private Collision hitDetection;
    private Collision damageRadius;
    private IMovement movement;
    private List<IEnemy> enemies;
    private final List<IEnemy> alreadyDamagedEnemies = new ArrayList<>();
    private ITower towerOwner;


    public Projectile() {

    }

    public Projectile(int health, Damage damage, double speed, Collision hitDetection) {
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.hitDetection = hitDetection;
    }

    public Projectile makeCopyWithTowerOwner() {
        return new Projectile(health, damage, speed, hitDetection);
    }

    public void setTowerOwner(ITower towerOwner) {
        this.towerOwner = towerOwner;
    }

    public void setLinearMovement(Point2D spawn, Point2D target) {
        this.movement = new LinearMovement(speed, spawn, target);
    }

    public void update(double deltaSteps) {
        movement.update(deltaSteps);
        updateCollision(deltaSteps);
    }

    public void setPenetrationDamage(Damage penetrationDamage) {
        this.penetrationDamage = penetrationDamage;
    }

    private void updateCollision(double deltaSteps) {
        hitDetection.setPosition(movement.getPosition());
        List<IEnemy> detectedEnemies = hitDetection.updateCollision(enemies);

        if (detectedEnemies.size() > 0) {

            this.applyDamage(penetrationDamage);


            if (isSingleTarget()) {
                IEnemy target = detectedEnemies.get(0);

                // Only apply damage to new targets
                if (!alreadyDamagedEnemies.contains(target)) {
                    targetHit(target);
                }

            } else {
                List<IEnemy> enemiesToDamage = damageRadius.updateCollision(detectedEnemies);
                for (IEnemy enemy: enemiesToDamage) {
                    // Only apply damage to new targets
                    if (!alreadyDamagedEnemies.contains(enemy)) {
                        targetHit(enemy);
                    }
                }
            }

        }
    }

    private void targetHit(IEnemy target) {
        int healthBefore = target.getHealth();

        target.applyDamage(damage);
        alreadyDamagedEnemies.add(target);

        // Add stats: Kills and damage done
        if (target.isDead()) {
            if (towerOwner != null) {
                towerOwner.addKills(1);
                towerOwner.addDamageDone(healthBefore);
            }
        } else {
            towerOwner.addDamageDone(damage.getDamageValue());
        }
    }

    private boolean isSingleTarget() {
        return damageRadius == null;
    }

    public boolean isDead() {
        return health <= 0;
    }

    protected void setEnemies(List<IEnemy> enemies) {
        this.enemies = enemies;
    }

    public void setDamageRadius(Collision damageRadius) {
        this.damageRadius = damageRadius;
    }

    public Point2D getPosition() {
        return movement.getPosition();
    }

    public Collision getCollision() {
        return null;
    }

    @Override
    public void applyDamage(Damage damage) {
        health = damage.applyDamage(health);
    }
}
