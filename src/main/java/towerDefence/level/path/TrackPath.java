package towerDefence.level.path;

import javax.sound.midi.Track;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class TrackPath {

    private final Point2D[] splineControls;
    private final List<PathPoint> splinePoints;

    public TrackPath(Point2D[] splineControls){
        this.splineControls = splineControls;
        splinePoints = calculateSpline(splineControls);
    }

    private List<PathPoint> calculateSpline(Point2D[] splineControls) {

        List<Point2D> splinePoints = new ArrayList<>();
        List<PathPoint> pathPoints = new ArrayList<>();


        Point2D previousPoint = new Point2D.Double(0.0, 0.0);
        for (double x = 0; x < (float) splineControls.length - 3.0 ; x += 0.05) {
            Point2D pos = getSplinePoint(x, splineControls);
            splinePoints.add(pos);

            Point2D direction = getPointDirection(previousPoint, pos);
            previousPoint = pos;
            pathPoints.add(new PathPoint(pos, direction));
        }

        return pathPoints;
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

    public List<PathPoint> getSplinePoints() {
        return splinePoints;
    }
}
