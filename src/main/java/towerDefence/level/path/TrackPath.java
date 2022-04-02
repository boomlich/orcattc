package towerDefence.level.path;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class TrackPath {

    private final List<Point2D> splineControls;
    private final List<Point2D> splinePoints;

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
        int p0, p1, p2, p3;
        p1 = (int) x + 1;
        p2 = p1 + 1;
        p3 = p2 + 1;
        p0 = p1 - 1;

        x = x - (int) x;
        double x2 = x * x;
        double x3 = x2 * x;

        double q0 = -x3 + 2.0*x2 - x;
        double q1 = 3.0*x3 - 5.0*x2 + 2.0;
        double q2 = -3.0*x3 + 4.0*x2 + x;
        double q3 = x3 - x2;

        double xValue = 0.5 * (splineControls.get(p0).getX() * q0 + splineControls.get(p1).getX() * q1 +
                splineControls.get(p2).getX() * q2 + splineControls.get(p3).getX() * q3);
        double yValue = 0.5 * (splineControls.get(p0).getY() * q0 + splineControls.get(p1).getY() * q1 +
                splineControls.get(p2).getY() * q2 + splineControls.get(p3).getY() * q3);

        return new Point2D.Double(xValue, yValue);
    }

    private Point2D getSplineGradient() {
        return null;
    }



    public List<Point2D> getSplineControls() {
        return splineControls;
    }

    public List<Point2D> getSplinePoints() {
        return splinePoints;
    }
}
