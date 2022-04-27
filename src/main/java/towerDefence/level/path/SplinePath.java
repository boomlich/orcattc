package towerDefence.level.path;

import towerDefence.Math.MathHelperMethods;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class SplinePath {

    private final SplinePathData splinePathData;
    private final Point2D[] splineControls;

    public SplinePath(Point2D[] splineControls){
        this.splineControls = splineControls;
        splinePathData = calculateSpline(splineControls, 10);
    }

    protected SplinePathData calculateSpline(Point2D[] splineControls, int splineResolution) {
        List<PathPoint> pathPoints = new ArrayList<>();
        List<Double> segmentLength = new ArrayList<>();

        Point2D currentPoint = MathHelperMethods.getSplinePoint(0, splineControls);
        double length = 0;
        double increment = 1 / (double) splineResolution;
        for (double x = increment; x < (double) splineControls.length - 3.0 ; x += increment) {
            Point2D nextPoint = MathHelperMethods.getSplinePoint(x, splineControls);
            Point2D direction = getPointRotation(currentPoint, nextPoint);
            currentPoint = nextPoint;
            pathPoints.add(new PathPoint(nextPoint, direction));

            // Calculate segment length
            length += MathHelperMethods.vectorLength(direction);
            if (pathPoints.size() % splineResolution == 0) {
                segmentLength.add(length);
                length = 0;
            }
        }
        if (length > 0) {
            segmentLength.add(length);
        }
        return new SplinePathData(pathPoints, splineResolution, segmentLength, splineControls);
    }

    private Point2D getPointRotation(Point2D posA, Point2D posB) {
        double x = posB.getX() - posA.getX();
        double y = posB.getY() - posA.getY();

        return new Point2D.Double(x, y);
    }

    public Point2D[] getSplineControls() {
        return splineControls;
    }

    public SplinePathData getSplinePathData() {
        return splinePathData;
    }
}
