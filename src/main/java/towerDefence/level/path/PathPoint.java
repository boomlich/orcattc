package towerDefence.level.path;

import java.awt.geom.Point2D;

public class PathPoint {

    public final Point2D point;
    public final double angle;
    public final double distance;

    PathPoint(Point2D point, double angle, double distance) {
        this.point = point;
        this.angle = angle;
        this.distance = distance;
    }
}
