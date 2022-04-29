package towerDefence.components.Weapons;

import towerDefence.model.GameEntities;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;

public class Weapon {
    /**
     * Time in ms between each shot
     */
    private final int fireFrequency;

    /**
     * Type of projectile that the weapon fires
     */
    private final IProjectile projectile;

    /**
     * Location where the projectile spawns upon the weapon firing
     */
    private Point2D projectileSpawn = new Point2D.Double(270, 500);
    private int fireCountdown;
    private Point2D target = new Point2D.Double(0, 0);
    private GameEntities gameEntities;

    /**
     * Owner of the weapon
     */
    private ITower tower;

    public Weapon(int fireFrequency, IProjectile projectile) {
        this.fireFrequency = fireFrequency;
        this.projectile = projectile;

        fireCountdown = fireFrequency;
    }

    public void setTowerOwner(ITower tower) {
        this.tower = tower;
    }

    public void update(double deltaSteps) {
        fireCountdown -= 1000/60 * deltaSteps;

        if (fireCountdown < 0) {
            fireProjectile();
            fireCountdown = fireFrequency;
        }
    }

    private void fireProjectile() {
        projectile.fireProjectile(projectileSpawn, target, tower, gameEntities);
    }

    /**
     * @param projectileSpawn projectile spawn location upon firing weapon
     */
    public void setProjectileSpawn(Point2D projectileSpawn) {
        this.projectileSpawn = projectileSpawn;
    }

    /**
     * @param target target to aim and shoot for
     */
    public void setTarget(Point2D target) {
        this.target = target;
    }

    public void setGameEntities(GameEntities gameEntities) {
        this.gameEntities = gameEntities;
    }

    /**
     * @return the weapons projectile that will be shot upon firing
     */
    public IProjectile getProjectile() {
        return projectile;
    }

    /**
     * @param percentageDelta percentage increase of projectile damage
     */
    public void increaseDamage(double percentageDelta) {
        projectile.increaseDamage(percentageDelta);
    }

    /**
     * @param penetrationDelta increase in number of penetrations before projectile is destroyed
     */
    public void increasePenetration (int penetrationDelta) {
        projectile.increasePenetration(penetrationDelta);
    }

    /**
     * @param numberOfShots number of shots to fire at once.
     * @return true if successfully increased the number of projectiles.
     */
    public boolean increaseSpreadShots(int numberOfShots){
        if (projectile instanceof ProjectileMultiShot) {
            ((ProjectileMultiShot) projectile).setNumberOfShots(numberOfShots);
            return true;
        }
        return false;
    }
}
