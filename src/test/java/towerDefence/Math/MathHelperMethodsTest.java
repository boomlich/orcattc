package towerDefence.Math;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathHelperMethodsTest {

    @Test
    void vectorWithForceTest() {
        double force = 2.0;
        Point2D startPoint = new Point2D.Double(1.0, 1.0);

        // Both axis
        Point2D endPoint = new Point2D.Double(4.0, 5.0);
        assertEquals(new Point2D.Double(1.2, 1.6), MathHelperMethods.vectorWithForce(startPoint, endPoint, force));

        // One axis
        endPoint = new Point2D.Double(5.0, 1.0);
        assertEquals(new Point2D.Double(2.0, 0.0), MathHelperMethods.vectorWithForce(startPoint, endPoint, force));

        // Negative
        force = 5.0;
        endPoint = new Point2D.Double(-8.0, -11.0);
        assertEquals(new Point2D.Double(-3.0, -4.0), MathHelperMethods.vectorWithForce(startPoint, endPoint, force));
    }

    @Test
    void vectorLengthTest() {
        Point2D vector = new Point2D.Double(4.0, 3.0);
        assertEquals(5.0, MathHelperMethods.vectorLength(vector));

        vector = new Point2D.Double(2 * Math.sqrt(155), 5.5);
        assertEquals(25.5, MathHelperMethods.vectorLength(vector));
    }
}
