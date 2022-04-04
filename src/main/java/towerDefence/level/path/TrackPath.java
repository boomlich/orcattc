package towerDefence.level.path;

import javax.sound.midi.Track;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class TrackPath {

    private final List<Point2D> splineControls;
    private final List<Point2D> splinePoints;
    private List<PathPoint> pathPoints;

    public TrackPath(List<Point2D> splineControls){
        this.splineControls = splineControls;
        splinePoints = calculateSpline(splineControls);
    }

    private List<Point2D> calculateSpline(List<Point2D> splineControls) {

        List<Point2D> splinePoints = new ArrayList<>();

        for (double x = 0; x < (float) splineControls.size() - 3.0 ; x += 0.05) {
            Point2D pos = getSplinePoint(x, splineControls);
            splinePoints.add(pos);
        }

        return splinePoints;
    }

    private Point2D getSplinePoint(double x, List<Point2D> splineControls) {
        int point0, point1, point2, point3;
        point1 = (int) x + 1;
        point2 = point1 + 1;
        point3 = point2 + 1;
        point0 = point1 - 1;

        x = x - (int) x;
        double x2 = x * x;
        double x3 = x2 * x;

        // Equations
        double equation1 = -x3 + 2.0*x2 - x;
        double equation2 = 3.0*x3 - 5.0*x2 + 2.0;
        double equation3 = -3.0*x3 + 4.0*x2 + x;
        double equation4 = x3 - x2;

        double xValue = 0.5 *
                (splineControls.get(point0).getX() * equation1
                + splineControls.get(point1).getX() * equation2
                + splineControls.get(point2).getX() * equation3
                + splineControls.get(point3).getX() * equation4);

        double yValue = 0.5 *
                (splineControls.get(point0).getY() * equation1
                + splineControls.get(point1).getY() * equation2
                + splineControls.get(point2).getY() * equation3
                + splineControls.get(point3).getY() * equation4);

        return new Point2D.Double(xValue, yValue);
    }

    private double getPointRotation(Point2D pointA, Point2D pointB) {
        double sinAngle = Math.abs(pointA.getY() - pointB.getY()) / Math.sqrt(
                Math.pow(pointB.getX() - pointA.getX(), 2) + Math.pow(pointB.getY() - pointA.getY(), 2));

        if (pointB.getX() < pointA.getX()) {
            return Math.PI - Math.asin(sinAngle);
        } else {
            return Math.asin(sinAngle);
        }
    }

    public List<Point2D> getSplineControls() {
        return splineControls;
    }

    public List<Point2D> getSplinePoints() {
        return splinePoints;
    }
}
