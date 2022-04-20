package towerDefence.components;

import towerDefence.Math.MathHelperMethods;
import towerDefence.components.damage.Damage;
import towerDefence.model.GameEntities;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;

public class ProjectileTripleShot extends Projectile{

    public ProjectileTripleShot(int health, Damage damage, double speed, Collision hitDetection) {
        super(health, damage, speed, hitDetection);
    }

    @Override
    public void fireProjectile(Point2D spawn, Point2D target, ITower towerOwner, GameEntities gameEntities) {
        for (int i = -1; i < 2; i++) {
            Projectile copy = this.makeCopy();
            Point2D targetOffset = calculateTargetOffset(i);
            copy.fireProjectile(spawn, targetOffset, towerOwner, gameEntities);
        }
    }

    private Point2D calculateTargetOffset(double offsetMultiplier) {
        Point2D targetPoint = getMovement().getTargetPoint();
        Point2D velocityNormal = MathHelperMethods.normalVector(getMovement().getVelocity());

        double magnitude = 5;

        return new Point2D.Double(
                targetPoint.getX() + velocityNormal.getX() * offsetMultiplier * magnitude,
                targetPoint.getY() + velocityNormal.getY() * offsetMultiplier * magnitude);
    }
}
