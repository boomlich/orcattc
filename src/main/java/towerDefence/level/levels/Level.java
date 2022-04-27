package towerDefence.level.levels;

import towerDefence.view.ImageLoader;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Level {

    private final String[] enemyWaves;
    private final Point2D[] pathSplineControls;
    private final int startMoney;
    private final int waveEndMoney;
    private BufferedImage mapGraphics;
    private int startHealth;

    public static final Level A = new Level(
            // Spline control points
            new Point2D[] {
            new Point2D.Double(-47, 183),
            new Point2D.Double(0, 183),
            new Point2D.Double(70, 183),
            new Point2D.Double(135, 175),
            new Point2D.Double(136, 122),
            new Point2D.Double(165, 98),
            new Point2D.Double(220, 97),
            new Point2D.Double(250, 126),
            new Point2D.Double(250, 168),
            new Point2D.Double(273, 182),
            new Point2D.Double(318, 186),
            new Point2D.Double(328, 215),
            new Point2D.Double(312, 241),
            new Point2D.Double(309, 277),
            new Point2D.Double(261, 308),
                    new Point2D.Double(193, 328),
                    new Point2D.Double(145, 352),
                    new Point2D.Double(119, 387),
                    new Point2D.Double(139, 421),
                    new Point2D.Double(187, 438),
                    new Point2D.Double(247, 445),
                    new Point2D.Double(315, 450),
                    new Point2D.Double(383, 445),
                    new Point2D.Double(443, 428),
                    new Point2D.Double(505, 410),
                    new Point2D.Double(540, 376),
                    new Point2D.Double(543, 325),
                    new Point2D.Double(527, 270),
                    new Point2D.Double(516, 208),

                    new Point2D.Double(511, 154),
                    new Point2D.Double(524, 109),
                    new Point2D.Double(561, 86),
                    new Point2D.Double(606, 98),
                    new Point2D.Double(645, 130),
                    new Point2D.Double(670, 172),
                    new Point2D.Double(681, 212),
                    new Point2D.Double(697, 263),
                    new Point2D.Double(715, 308),
                    new Point2D.Double(745, 332),
                    new Point2D.Double(801, 332),
                    new Point2D.Double(857, 336),
                    new Point2D.Double(919, 339),
                    new Point2D.Double(981, 343),
                    new Point2D.Double(1022, 343),
            },

            // Enemy wave
            new String[] {
                    "P_60, D_45, ab_10",
                    "P_120, D_400, abcdefgh_2",
                    "P_120, D_30, ab_6, c_1, P_500, ab_10",
            },
            10000,
            100,
            250,
            "graphics/Map/Map_01.png"
    );

    private Level(Point2D[] pathSplineControls, String[] enemyWaves, int startMoney, int startHealth, int waveEndMoney, String mapGraphicsPath){
        this.pathSplineControls = pathSplineControls;
        this.enemyWaves = enemyWaves;
        this.startMoney = startMoney;
        this.waveEndMoney = waveEndMoney;
        this.startHealth = startHealth;
        setMapGraphics(mapGraphicsPath);
    }

    private void setMapGraphics(String path) {
        try {
            mapGraphics = ImageLoader.loadBufferedImage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getEnemyWave(int selectedWave) {
        return enemyWaves[selectedWave];
    }

    public Point2D[] getPathSplineControls() {
        return pathSplineControls;
    }

    public int getMaxWaves() {
        return enemyWaves.length;
    }

    public int getStartMoney() {
        return startMoney;
    }

    public int getWaveEndMoney() {
        return waveEndMoney;
    }

    public BufferedImage getMapBackground() {
        return mapGraphics;
    }

    public int getStartHealth() {
        return startHealth;
    }
}
