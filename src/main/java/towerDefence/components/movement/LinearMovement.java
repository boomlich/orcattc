package towerDefence.components.movement;

import towerDefence.Math.MathHelperMethods;

import java.awt.geom.Point2D;

public class LinearMovement {

    private Point2D velocity;
    private Point2D position;

    public LinearMovement(double speed, Point2D spawnPoint, Point2D targetPoint) {
        calculateVelocity(spawnPoint, targetPoint, speed);
        position = spawnPoint;
    }

    private void calculateVelocity(Point2D spawnPoint, Point2D targetPoint, double speed) {
        velocity = MathHelperMethods.vectorWithForce(spawnPoint, targetPoint, speed);
    }

    public void update(double deltaSteps) {
        position = applyVelocity(deltaSteps, position);
    }

    private Point2D applyVelocity(double deltaSteps, Point2D currentLocation) {

        double x = currentLocation.getX() + (velocity.getX() * deltaSteps);
        double y = currentLocation.getY() + (velocity.getY() * deltaSteps);

        return new Point2D.Double(x, y);
    }

    public Point2D getPosition() {
        return position;
    }
}
