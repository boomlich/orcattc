package towerDefence.level.path;

import towerDefence.Math.MathHelperMethods;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class SplinePath {

    private final SplinePathData splinePathData;
    private final Point2D[] splineControls;

    public SplinePath(Point2D[] splineControls){
        this.splineControls = splineControls;
        splinePathData = calculateSpline(splineControls, 20);
        calculateSplineLength(splinePathData, 20);
    }


    private void calculateSplineLength(SplinePathData pathData, int pathResolution) {
        double totalLength = 0.0;
        List<Double> segmentLengths = new ArrayList<>();
        List<Double> allSegments = new ArrayList<>();
        System.out.println(pathData.getPathPoints().size());

        double length = 0.0;
        int point = 0;
        for (int i = 0; i < pathData.getPathPoints().size() - 1; i++) {
            Point2D current = pathData.getPathPoints().get(i).coordinate;
            Point2D next = pathData.getPathPoints().get(i + 1).coordinate;

            double x = next.getX() - current.getX();
            double y = next.getY() - current.getY();

            allSegments.add(MathHelperMethods.vectorLength(new Point2D.Double(x, y)));

            length += MathHelperMethods.vectorLength(new Point2D.Double(x, y));
            point ++;
            if (point % 20 == 0) {
                segmentLengths.add(length);
                length = 0;
            }
            totalLength += MathHelperMethods.vectorLength(new Point2D.Double(x, y));
        }
        if (length > 0.0) {
            segmentLengths.add(length);
        }


        System.out.println("Total points: " + point);
        System.out.println(segmentLengths);
        System.out.println("OLD total length: " + splinePathData.getTotalLength());
        System.out.println("TOTAL length: "  + totalLength);

        double total = 0;
        for (double num: allSegments) {
            total += num;
        }
        System.out.println("ADDED TOTAL: " + total);
    }

    protected SplinePathData calculateSpline(Point2D[] splineControls, int splineResolution) {
        List<PathPoint> pathPoints = new ArrayList<>();
        List<Double> segmentLength = new ArrayList<>();

        Point2D currentPoint = getSplinePoint(0, splineControls);
        double length = 0;
        double increment = 1 / (double) splineResolution;
        for (double x = increment; x < (double) splineControls.length - 3.0 ; x += increment) {
            Point2D nextPoint = getSplinePoint(x, splineControls);
            Point2D direction = getPointDirection(currentPoint, nextPoint);
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

        for (int i = 0; i < segmentLength.size(); i++) {
            System.out.println(i + " : " + segmentLength.get(i));
        }

        return new SplinePathData(pathPoints, splineResolution, segmentLength, splineControls);
    }

    private Point2D getPointDirection (Point2D posA, Point2D posB) {
        double x = posB.getX() - posA.getX();
        double y = posB.getY() - posA.getY();

        return new Point2D.Double(x, y);
    }

    private Point2D getSplinePoint(double x, Point2D[] splineControls) {
        int point0, point1, point2, point3;
        point1 = (int) x + 1;
        point2 = point1 + 1;
        point3 = point2 + 1;
        point0 = point1 - 1;

        // Get x-value between 0-1
        x = x - (int) x;
        double x2 = x * x;
        double x3 = x2 * x;

        // Equations
        double equation1 = -x3 + 2.0*x2 - x;
        double equation2 = 3.0*x3 - 5.0*x2 + 2.0;
        double equation3 = -3.0*x3 + 4.0*x2 + x;
        double equation4 = x3 - x2;

        double xValue = 0.5 *
                (splineControls[point0].getX() * equation1
                + splineControls[point1].getX() * equation2
                + splineControls[point2].getX() * equation3
                + splineControls[point3].getX() * equation4);

        double yValue = 0.5 *
                (splineControls[point0].getY() * equation1
                + splineControls[point1].getY() * equation2
                + splineControls[point2].getY() * equation3
                + splineControls[point3].getY() * equation4);

        return new Point2D.Double(xValue, yValue);
    }

    public Point2D[] getSplineControls() {
        return splineControls;
    }

    public SplinePathData getSplinePathData() {
        return splinePathData;
    }
}
