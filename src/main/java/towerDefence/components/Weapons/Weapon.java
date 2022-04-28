package towerDefence.components.Weapons;

import towerDefence.model.GameEntities;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;

public class Weapon {
    /**
     * Time in ms between each shot
     */
    private int fireFrequency;

    /**
     * Type of projectile that the weapon fires
     */
    private IProjectile projectile;

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


    public Weapon() {
    }

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

    protected void fireProjectile() {
        projectile.fireProjectile(projectileSpawn, target, tower, gameEntities);
    }

    public void setProjectileSpawn(Point2D projectileSpawn) {
        this.projectileSpawn = projectileSpawn;
    }

    public void setTarget(Point2D target) {
        this.target = target;
    }

    public void setGameEntities(GameEntities gameEntities) {
        this.gameEntities = gameEntities;
    }

    public IProjectile getProjectile() {
        return projectile;
    }

    public void increaseDamage(double percentageDelta) {
        projectile.increaseDamage(percentageDelta);
    }

    /**
     * @param penetrationDelta increase in number of penetrations before projectile is destroyed
     */
    public void increasePenetration (int penetrationDelta) {
        projectile.increasePenetration(penetrationDelta);
    }

    public void increaseSpreadShots(int numberOfShots){
        if (projectile instanceof ProjectileMultiShot) {
            ((ProjectileMultiShot) projectile).setNumberOfShots(numberOfShots);
        }
    }
}
