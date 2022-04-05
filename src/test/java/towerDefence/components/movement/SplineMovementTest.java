package towerDefence.components.movement;

import org.junit.jupiter.api.Test;
import towerDefence.level.path.SplinePath;
import towerDefence.level.path.SplinePathData;

import java.awt.geom.Point2D;

public class SplineMovementTest {

    @Test
    void calculateNormalizedMovementTest() {
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

        System.out.println(splinePath.getTotalLength());

        System.out.println(movement.calculateNormalizedPosition(1));

    }
}
