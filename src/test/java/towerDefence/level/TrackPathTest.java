package towerDefence.level;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

public class TrackPathTest {

    @Test
    void getPointRotationTest() {
        Point2D pointA = new Point2D.Double(3.0, 3.0);
        Point2D pointB = new Point2D.Double(2.0, 5.0);

        double sinAngle = Math.abs(pointA.getY() - pointB.getY()) / Math.sqrt(
                Math.pow(pointB.getX() - pointA.getX(), 2) +
                Math.pow(pointB.getY() - pointA.getY(), 2));

        if (pointB.getX() < pointA.getX()) {
            System.out.println(Math.toDegrees(Math.PI - Math.asin(sinAngle)));
        } else {
            System.out.println(Math.toDegrees(Math.asin(sinAngle)));
        }
    }
}
