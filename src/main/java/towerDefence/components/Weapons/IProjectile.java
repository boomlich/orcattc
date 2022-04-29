package towerDefence.components.Weapons;

import towerDefence.components.collision.Collision;
import towerDefence.components.debuff.IDebuff;
import towerDefence.model.GameEntities;
import towerDefence.components.particles.Particle;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;

public interface IProjectile  {

    public Projectile makeCopy();

    public void fireProjectile(Point2D spawn, Point2D target, ITower towerOwner, GameEntities gameEntities);

    public void increasePenetration(int penetrationDelta);

    public void increaseDamage(double percentageDelta);

    public void setDebuff(IDebuff debuff);

    public void setDamageRadius(Collision damageRadius);

    public void setImpactEffect(Particle particle);

    boolean isDead();

}
