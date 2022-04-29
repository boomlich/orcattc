package towerDefence.level.components.movement;

import org.junit.jupiter.api.Test;
import towerDefence.components.movement.SplineMovement;
import towerDefence.level.path.SplinePath;
import towerDefence.level.path.SplinePathData;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SplineMovementTest {

    @Test
    void calculateNormalizedMovementAndLengthTest() {
        Point2D[] splineControls = {
                new Point2D.Double(-25, 0.0),
                new Point2D.Double(0, 0.0),
                new Point2D.Double(25, 0.0),
                new Point2D.Double(50, 0.0),
                new Point2D.Double(75, 0.0),
                new Point2D.Double(100, 0.0),
                new Point2D.Double(125, 0.0),
        };

        SplinePath testPath = new SplinePath(splineControls);
        SplinePathData splinePath = testPath.getSplinePathData();
        SplineMovement movement = new SplineMovement(splinePath, 1);

        assertEquals(97.5, splinePath.getTotalLength(), 0.5);
//        assertEquals(new Point2D.Double(5.0, 0.0), movement.calculateNormalizedPosition(5));
    }
}
