package towerDefence.level.path;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathPointTest {

    @Test
    void pathPointCalculationsTest() {
        PathPoint pathPoint = new PathPoint(new Point2D.Double(0, 0), new Point2D.Double(5.0, 7.0));

        // Orientation
        assertEquals(0.9505, pathPoint.rotation, 0.00005);

        // Unit normal vector
        assertEquals(-0.8137, pathPoint.unitNormalVector.getX(), 0.00005);
        assertEquals(0.5812, pathPoint.unitNormalVector.getY(), 0.00005);
    }

    @Test
    void pathPointRotationTest() {
        // First quadrant
        PathPoint pathPoint = new PathPoint(new Point2D.Double(0, 0), new Point2D.Double(5.0, 5.0));
        assertEquals(0.7854, pathPoint.rotation, 0.00005);

        // Second quadrant
        pathPoint = new PathPoint(new Point2D.Double(0, 0), new Point2D.Double(-5.0, 5.0));
        assertEquals(2.3562, pathPoint.rotation, 0.00005);


        // Third quadrant
        pathPoint = new PathPoint(new Point2D.Double(0, 0), new Point2D.Double(-5.0, -5.0));
        assertEquals(3.927, pathPoint.rotation, 0.00005);

        // Fourth quadrant
        pathPoint = new PathPoint(new Point2D.Double(0, 0), new Point2D.Double(5.0, -5.0));
        assertEquals(5.4979, pathPoint.rotation, 0.00005);
    }
}
