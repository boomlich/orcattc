package towerDefence.level.path;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

public class TrackPathTest {

    @Test
    void pointDirectionTest() {
        Point2D posA = new Point2D.Double(10, 6);
        Point2D posB = new Point2D.Double(14, 4);

        double x = Math.abs(posB.getX() - posA.getX());
        double y = Math.abs(posB.getY() - posA.getY());

        Point2D direction = new Point2D.Double(x, y);
        System.out.println(direction);

    }
}
