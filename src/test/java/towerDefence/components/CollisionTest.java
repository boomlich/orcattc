package towerDefence.components;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollisionTest {

    @Test
    void updateCollisionSingleTargetTest() {
        Collision mainCollision = new Collision(5);

        List<CollidableObject> testObjects = new ArrayList<>();
        Collision testCollision = new Collision(4);
        testCollision.setPosition(new Point2D.Double(8.9, 1.74));
        testObjects.add(testCollision);

        assertEquals(0, mainCollision.updateCollision(testObjects).size());

        testCollision.setPosition(new Point2D.Double(-6.4, -2));
        assertEquals(1, mainCollision.updateCollision(testObjects).size());

        testCollision.setPosition(new Point2D.Double(0.0, 0.0));
        assertEquals(1, mainCollision.updateCollision(testObjects).size());
    }

    @Test
    void updateCollisionMutlipleTargetsTest() {
        Collision mainCollision = new Collision(5, false);

        List<CollidableObject> testObjects = new ArrayList<>();
        Collision testCollision1 = new Collision(4);
        Collision testCollision2 = new Collision(4);

        testObjects.add(testCollision1);
        testObjects.add(testCollision2);

        // Two collisions
        testCollision1.setPosition(new Point2D.Double(-4, 2));
        testCollision1.setPosition(new Point2D.Double(2, 0));
        assertEquals(2, mainCollision.updateCollision(testObjects).size());

        // Only one collision
        testCollision1.setPosition(new Point2D.Double(4, 6));
        testCollision1.setPosition(new Point2D.Double(-6, 8));
        assertEquals(1, mainCollision.updateCollision(testObjects).size());

        // No collisions
        testCollision1.setPosition(new Point2D.Double(10, -4));
        testCollision2.setPosition(new Point2D.Double(-6, 8));
        assertEquals(0, mainCollision.updateCollision(testObjects).size());
    }
}
