package towerDefence.level.path;

import org.junit.jupiter.api.Test;
import towerDefence.Math.MathHelperMethods;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SplinePathTest {

    @Test
    void pointDirectionTest() {
        Point2D posA = new Point2D.Double(10, 6);
        Point2D posB = new Point2D.Double(14, 4);

        double x = Math.abs(posB.getX() - posA.getX());
        double y = Math.abs(posB.getY() - posA.getY());

        Point2D direction = new Point2D.Double(x, y);
        System.out.println(direction);

    }

    @Test
    void splineLengthCalculationTest() {
        Point2D[] splineControls = {
                new Point2D.Double(-1.0, 0.0),
                new Point2D.Double(0.0, 0.0),
                new Point2D.Double(15.0, 0.0),
                new Point2D.Double(20.0, 0.0),
                new Point2D.Double(30.0, 0.0),
                new Point2D.Double(55.0, 0.0),
                new Point2D.Double(85.0, 0.0),
                new Point2D.Double(100.0, 0.0),
                new Point2D.Double(101.0, 0.0),
        };

        double[] targetLengths = {15.0, 5.0, 10.0, 25.0, 30.0, 15.0};

        SplinePath testPath = new SplinePath(splineControls);
        SplinePathData splinePath = testPath.calculateSpline(splineControls, 20);

        // Individual segment length
        for (int i = 0; i < targetLengths.length; i++) {
            assertEquals(targetLengths[i], splinePath.getSegmentLength().get(i), 0.00005);
        }

        // Total path length
        assertEquals(100.0, splinePath.getTotalLength(), 0.00005);
    }
}
