package towerDefence.Math;

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

    public static Point2D unitVector(Point2D vector) {
        double length = vectorLength(vector);
        return new Point2D.Double(vector.getX() / length, vector.getY() / length);
    }


    public static Point2D normalVector(Point2D vector) {
        return new Point2D.Double(-vector.getY(), vector.getX());
    }
}
