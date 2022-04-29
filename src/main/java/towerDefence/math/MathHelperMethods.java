package towerDefence.math;

import java.awt.geom.Point2D;

public class MathHelperMethods {

    public static Point2D vectorWithAmplitude(Point2D startPoint, Point2D endPoint, double force) {
        Point2D baseVector = new Point2D.Double(endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());
        double vectorLength = vectorLength(baseVector);

        return new Point2D.Double((baseVector.getX() / vectorLength) * force, (baseVector.getY() / vectorLength) * force);
    }

    public static double vectorLength(Point2D vector) {
        return Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2));
    }

    public static double vectorLength(Point2D pointA, Point2D pointB) {
        return vectorLength(pointsToVector(pointA, pointB));
    }

    public static Point2D pointsToVector(Point2D pointA, Point2D pointB) {
        double x = pointB.getX() - pointA.getX();
        double y = pointA.getY() - pointB.getY();

        return new Point2D.Double(x, y);
    }

    public static Point2D unitVector(Point2D vector) {
        double length = vectorLength(vector);
        return new Point2D.Double(vector.getX() / length, vector.getY() / length);
    }

    public static Point2D normalVector(Point2D vector) {
        return new Point2D.Double(-vector.getY(), vector.getX());
    }


    public static Point2D getSplinePoint(double x, Point2D[] splineControls) {

        // Code inspired by the One Lone Coder
        // source: https://github.com/OneLoneCoder/videos/blob/master/OneLoneCoder_Splines2.cpp

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

    public static double calculateVectorAngle(Point2D direction) {
        double sinAngle = direction.getY() / Math.sqrt(Math.pow(direction.getX(), 2) + Math.pow(direction.getY(), 2));

        if (direction.getX() < 0) {
            return Math.PI - Math.asin(sinAngle);
        } else {
            return Math.asin(sinAngle);
        }
    }


}
