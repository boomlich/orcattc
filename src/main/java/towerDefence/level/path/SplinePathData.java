package towerDefence.level.path;

import towerDefence.components.Collision;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class SplinePathData {
    private final List<PathPoint> pathPoints;

    /** Number of points per segment */
    private final int segmentResolution;

    private final List<Double> segmentLength;
    private final double totalLength;

    private final Point2D[] splineControls;

    private final List<Collision> pathCollision;

    protected SplinePathData(List<PathPoint> pathPoints, int segmentResolution,
                             List<Double> segmentLength, Point2D[] splineControls) {
        this.pathPoints = pathPoints;
        this.segmentResolution = segmentResolution;
        this.segmentLength = segmentLength;
        this.splineControls = splineControls;
        totalLength = calculateTotalLength(segmentLength);
        pathCollision = calculatePathCollision(pathPoints, 4);
    }

    private double calculateTotalLength (List<Double> segmentLength) {
        double totalLength = 0;
        for (Double segment: segmentLength) {
            totalLength += segment;
        }
        return totalLength;
    }

    /**
     * Add a collision circle at a regular interval on the path points.
     *
     * @param pathPoints
     * @param intervalLength the number of points between each collision circle
     * @return List of all collisions circles distributed on the path points
     */
    private List<Collision> calculatePathCollision(List<PathPoint> pathPoints, int intervalLength) {

        double radius = 20;

        List<Collision> collisions = new ArrayList<>();
        for (int i = 0; i < pathPoints.size(); i++) {
            if (i % intervalLength == 0) {
                Collision collision = new Collision(radius);
                collision.setPosition(new Point2D.Double(
                        pathPoints.get(i).coordinate.getX(),
                        pathPoints.get(i).coordinate.getY()));
                collisions.add(collision);
            }
        }
        return collisions;
    }

    public List<PathPoint> getPathPoints() {
        return pathPoints;
    }

    public int getSegmentResolution() {
        return segmentResolution;
    }

    public List<Double> getSegmentLength() {
        return segmentLength;
    }

    public double getTotalLength() {
        return totalLength;
    }

    public Point2D[] getSplineControls() {
        return splineControls;
    }

    public List<Collision> getPathCollision() {
        return pathCollision;
    }
}
