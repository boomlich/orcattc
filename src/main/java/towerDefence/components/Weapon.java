package towerDefence.components;

import towerDefence.model.GameEntities;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;

public class Weapon {
    /**
     * Time in ms between each shot
     */
    private int fireFrequency;
    private Projectile projectile;
    private Point2D projectileSpawn = new Point2D.Double(270, 500);
    private int fireCountdown;
    private Point2D target = new Point2D.Double(0, 0);
    private GameEntities gameEntities;
    private ITower tower;


    public Weapon() {

    }

    public Weapon(int fireFrequency, Projectile projectile) {
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
        Projectile firedProjectile = projectile.makeCopyWithTowerOwner();
        firedProjectile.setTowerOwner(tower);
        firedProjectile.setLinearMovement(projectileSpawn, target);
        firedProjectile.setEnemies(gameEntities.getSortedEnemies());
        gameEntities.addProjectile(firedProjectile);

    }

    public void setFireFrequency(int fireFrequency) {
        this.fireFrequency = fireFrequency;
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

    public void addKills(int kills) {
        tower.addKills(kills);
    }

    public void addDamageDone(int damage) {
        tower.addDamageDone(damage);
    }
}
