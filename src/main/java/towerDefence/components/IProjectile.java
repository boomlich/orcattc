package towerDefence.components;

import towerDefence.components.debuff.IDebuff;
import towerDefence.model.GameEntities;
import towerDefence.particles.Particle;
import towerDefence.particles.ParticleEmitter;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;

public interface IProjectile {

    public Projectile makeCopy();

    public void fireProjectile(Point2D spawn, Point2D target, ITower towerOwner, GameEntities gameEntities);

    public void increasePenetration(int penetrationDelta);

    public void increaseDamage(double percentageDelta);

    public void setDebuff(IDebuff debuff);

    public void setDamageRadius(Collision damageRadius);

    public void setParticleEmitter(ParticleEmitter particleEmitter);

    public void setImpactEffect(Particle particle);

}
