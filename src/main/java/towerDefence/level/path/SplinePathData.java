package towerDefence.level.path;

import java.awt.geom.Point2D;
import java.util.List;

public class SplinePathData {
    private final List<PathPoint> pathPoints;

    /** Number of points per segment */
    private final int segmentResolution;

    private final List<Double> segmentLength;
    private final double totalLength;

    private final Point2D[] splineControls;

    protected SplinePathData(List<PathPoint> pathPoints, int segmentResolution,
                             List<Double> segmentLength, Point2D[] splineControls) {
        this.pathPoints = pathPoints;
        this.segmentResolution = segmentResolution;
        this.segmentLength = segmentLength;
        this.splineControls = splineControls;
        totalLength = calculateTotalLength(segmentLength);
    }

    private double calculateTotalLength (List<Double> segmentLength) {
        double totalLength = 0;
        for (Double segment: segmentLength) {
            totalLength += segment;
        }
        return totalLength;
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
}
