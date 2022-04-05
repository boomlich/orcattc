package towerDefence.level.levels;

import java.awt.geom.Point2D;

public class Level {

    private final String[] enemyWaves;
    private final Point2D[] pathSplineControls;

    public static final Level A = new Level(
            // Spline control points
            new Point2D[] {
            new Point2D.Double(100, 125),
            new Point2D.Double(157, 166),
            new Point2D.Double(200, 100),
            new Point2D.Double(300, 90),
            new Point2D.Double(341, 134),
            new Point2D.Double(305, 181),
            new Point2D.Double(256, 144),
            new Point2D.Double(200, 200),
            new Point2D.Double(235, 235),
            new Point2D.Double(250, 250),
            },

            // Enemy wave
            new String[] {
            "D_15, a_10, D_30, ba_4, D_60, ab_2",
            }
    );

    private Level(Point2D[] pathSplineControls, String[] enemyWaves){
        this.pathSplineControls = pathSplineControls;
        this.enemyWaves = enemyWaves;
    }

    public String getEnemyWave(int selectedWave) {
        return enemyWaves[selectedWave];
    }

    public Point2D[] getPathSplineControls() {
        return pathSplineControls;
    }
}