package towerDefence.components.Weapons;

import towerDefence.components.collision.Collision;
import towerDefence.components.debuff.IDebuff;
import towerDefence.model.GameEntities;
import towerDefence.components.particles.Particle;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;

public interface IProjectile  {

    /**
     * @return copy of the projectile
     */
    Projectile makeCopy();

    /**
     * Make copy of the projectile, calculate velocity and movement, and add the
     * copied projectile to the game entities.
     *
     * @param spawn position where the projectile will spawn when firering
     * @param target position where the projectile will travel towards
     * @param towerOwner tower that fires the projectile
     * @param gameEntities entity manger
     */
    void fireProjectile(Point2D spawn, Point2D target, ITower towerOwner, GameEntities gameEntities);

    /**
     * @param penetrationDelta change in health value for the projectile.
     */
    void increasePenetration(int penetrationDelta);

    /**
     * @param percentageDelta change in damage value for the projectile.
     */
    void increaseDamage(double percentageDelta);

    /**
     * @param debuff debuff that will be applied upon impact with targets
     */
    void setDebuff(IDebuff debuff);

    /**
     * @param towerOwner tower that fires the projectile
     */
    void setTowerOwner(ITower towerOwner);

    /**
     * @param damageRadius radius of area of effect damage
     */
    void setDamageRadius(Collision damageRadius);

    /**
     * @param particle particle to display upon impact with target
     */
    void setImpactEffect(Particle particle);
}
