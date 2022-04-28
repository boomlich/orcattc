package towerDefence.components.Weapons;

import towerDefence.components.Collision.Collision;
import towerDefence.components.damage.Damage;
import towerDefence.components.damage.IDamageable;
import towerDefence.components.debuff.IDebuff;
import towerDefence.components.movement.LinearMovement;
import towerDefence.enemies.IEnemy;
import towerDefence.model.GameEntities;
import towerDefence.particles.Particle;
import towerDefence.particles.ParticleEmitter;
import towerDefence.tower.ITower;
import towerDefence.view.IRenderableObject;
import towerDefence.view.sprite.Sprite;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Projectile implements IDamageable, IProjectile, IRenderableObject {

    private int health;
    private Damage damage;
    private final Damage penetrationDamage = new Damage(100);
    private double speed;
    private Collision hitDetection;
    private Collision damageRadius;
    private LinearMovement movement;
    private List<IEnemy> enemies;
    private final List<IEnemy> alreadyDamagedEnemies = new ArrayList<>();
    private ITower towerOwner;
    private IDebuff debuff;
    private GameEntities gameEntities;
    private Particle impactEffect;
    private int timeLeftToLive = 5000;
    private boolean isDead;


    public Projectile() {
    }

    public Projectile(int health, Damage damage, double speed, Collision hitDetection) {
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.hitDetection = hitDetection;
    }

    public void fireProjectile(Point2D spawn, Point2D target, ITower towerOwner, GameEntities gameEntities) {
        Projectile fired = this.makeCopy();
        fired.setTowerOwner(towerOwner);
        fired.setLinearMovement(spawn, target);
        fired.setGameEntities(gameEntities);
        fired.setEnemies(gameEntities.getSortedEnemies(false));
        if (impactEffect != null) {
            fired.setImpactEffect(impactEffect.makeCopyWithPosition(null));
        }
        fired.setDebuff(debuff);
        fired.setDamageRadius(damageRadius);
        gameEntities.addProjectile(fired);
    }

    protected void setGameEntities(GameEntities gameEntities) {
        this.gameEntities = gameEntities;
    }

    public void setImpactEffect(Particle particle) {
        impactEffect = particle;
    }

    @Override
    public void setParticleEmitter(ParticleEmitter particleEmitter) {
    }

    public Projectile makeCopy() {
        return new Projectile(health, damage, speed, hitDetection);
    }

    public void setTowerOwner(ITower towerOwner) {
        this.towerOwner = towerOwner;
    }

    public void setLinearMovement(Point2D spawn, Point2D target) {
        this.movement = new LinearMovement(speed, spawn, target);
    }

    @Override
    public int getZDepth() {
        return 0;
    }

    @Override
    public Sprite getSprite() {
        return null;
    }

    public void update(double deltaSteps) {
        movement.update(deltaSteps);
        updateCollision(deltaSteps);

        if (impactEffect != null) {
            impactEffect.update(deltaSteps);
            if (impactEffect.isDead()) {
                impactEffect = null;
            }
        }

        timeLeftToLive -= 1000/60 * deltaSteps;
        if (timeLeftToLive <= 0 || health <= 0) {
            death();
        }
    }

    private void death() {
        isDead = true;
        System.out.println("DEATH");
    }

    private void updateCollision(double deltaSteps) {
        hitDetection.setPosition(movement.getPosition());

        List<IEnemy> detectedEnemies = hitDetection.updateCollision(enemies);

        if (detectedEnemies.size() > 0) {

            // Apply self damage
            this.applyDamage(penetrationDamage);

            if (isSingleTarget()) {
                IEnemy target = detectedEnemies.get(0);

                // Only apply damage to new targets
                if (!alreadyDamagedEnemies.contains(target)) {
                    targetHit(target);
                }
            }
            else {
                damageRadius.setPosition(movement.getPosition());
                List<IEnemy> enemiesToDamage = damageRadius.updateCollision(enemies);
                for (IEnemy enemy: enemiesToDamage) {
                    targetHit(enemy);
                }
            }
        }
    }

    private void targetHit(IEnemy target) {
        int healthBefore = target.getHealth();

        // Only display impact effect once
        if (impactEffect != null) {
            gameEntities.addParticle(impactEffect.makeCopyWithPosition(getPosition()));
            impactEffect = null;
        }

        target.applyDamage(damage);
        if (debuff != null) {
            target.applyDebuff(debuff.makeCopy());
        }

        alreadyDamagedEnemies.add(target);

        if (towerOwner != null) {
            towerOwner.addStats(target, healthBefore, damage);
        }
    }

    private boolean isSingleTarget() {
        return damageRadius == null;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    protected void setEnemies(List<IEnemy> enemies) {
        this.enemies = enemies;
    }

    @Override
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

    public void increasePenetration (int penetrationDelta) {
        health += penetrationDamage.getDamageValue() * penetrationDelta;
    }

    public void increaseDamage(double percentageDelta) {
        damage = new Damage((int) (damage.getDamageValue() + (1.0 + percentageDelta)));
    }

    @Override
    public void setDebuff(IDebuff debuff) {
        this.debuff = debuff;
    }
}
