package towerDefence.level.path;

import java.util.List;

public class SplinePathData {
    private final List<PathPoint> pathPoints;

    /** Number of points per segment */
    private final int segmentResolution;

    private final List<Double> segmentLength;
    private final double totalLength;

    protected SplinePathData(List<PathPoint> pathPoints, int segmentResolution, List<Double> segmentLength) {
        this.pathPoints = pathPoints;
        this.segmentResolution = segmentResolution;
        this.segmentLength = segmentLength;
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
}
