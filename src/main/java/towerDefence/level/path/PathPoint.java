package towerDefence.level.path;

import towerDefence.math.MathHelperMethods;

import java.awt.geom.Point2D;

public class PathPoint {

    public final Point2D coordinate;
    public final Point2D direction;
    public final double rotation;
    public final Point2D unitNormalVector;

    PathPoint(Point2D coordinate, Point2D direction) {
        this.coordinate = coordinate;
        this.direction = direction;
        rotation = getPointRotation(direction);
        unitNormalVector = MathHelperMethods.normalVector(MathHelperMethods.unitVector(direction));
    }

    private double getPointRotation(Point2D direction) {
        double sinAngle = direction.getY() / Math.sqrt(Math.pow(direction.getX(), 2) + Math.pow(direction.getY(), 2));

        if (direction.getX() < 0) {
            return Math.PI - Math.asin(sinAngle);
        } else {
            return Math.asin(sinAngle);
        }
    }
}
