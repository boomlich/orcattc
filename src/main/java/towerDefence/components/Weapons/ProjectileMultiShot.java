package towerDefence.components.Weapons;

import towerDefence.components.collision.Collision;
import towerDefence.components.damage.Damage;
import towerDefence.model.GameEntities;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;

public class ProjectileMultiShot extends Projectile{

    private int numberOfShots = 1;

    public ProjectileMultiShot(int health, Damage damage, double speed, Collision hitDetection) {
        super(health, damage, speed, hitDetection);
    }

    @Override
    public void fireProjectile(Point2D spawn, Point2D target, ITower towerOwner, GameEntities gameEntities) {

        double fireAngleSpread = Math.PI / 4;
        double anglePerShot = fireAngleSpread / (numberOfShots - 1);

        if (numberOfShots == 1) {
            this.makeCopy().fireProjectile(spawn, target, towerOwner, gameEntities);
        } else {
            for (int i = 0; i < numberOfShots; i++) {
                Projectile copy = this.makeCopy();
                double shotAngle = ((-fireAngleSpread) / 2.0) + anglePerShot * i;
                Point2D newTarget = calculateTargetOffset(spawn, target, shotAngle);
                copy.fireProjectile(spawn, newTarget, towerOwner, gameEntities);
            }
        }
    }

    private Point2D newVelocity(Point2D a, Point2D b) {
        return new Point2D.Double(b.getX() - a.getX(), b.getY() - a.getY());
    }

    private Point2D calculateTargetOffset(Point2D spawn, Point2D target, double angle) {
        Point2D velocity = newVelocity(spawn, target);

        double offsetX = velocity.getX() * Math.cos(angle) + velocity.getY() * Math.sin(angle);
        double offsetY = (-velocity.getX()) * Math.sin(angle) + velocity.getY() * Math.cos(angle);

        return new Point2D.Double(spawn.getX() + offsetX, spawn.getY() + offsetY);
    }

    /**
     * @param numberOfShots number of projectiles fired at once.
     */
    public void setNumberOfShots(int numberOfShots) {
        this.numberOfShots = numberOfShots;
    }
}
