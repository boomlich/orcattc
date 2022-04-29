package towerDefence.level;

import towerDefence.view.ImageLoader;
import UI.Interaction.InteractCode;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Level {

    /**
     * All enemy waves to spawn in the level
     */
    private final String[] enemyWaves;

    /**
     * The levels path control points. Used to calculate the path.
     */
    private final Point2D[] pathSplineControls;

    /**
     * Initial money upon loading the level
     */
    private final int startMoney;

    /**
     * money earned upon finishing a round of waves.
     */
    private final int waveEndMoney;

    /**
     * Level selection button thumbnail image path
     */
    private final String thumbnailNormalPath, thumbnailHoverPath;

    /**
     * Background graphics of the level.
     */
    private BufferedImage mapGraphics;

    /**
     * Initial player health value upon loading the level
     */
    private final int startHealth;

    /**
     * Level selection details
     */
    private final String levelTitle, levelDescription;

    /**
     * Level selection button interaction-code
     */
    private final InteractCode interactCode;

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
//                    "P_60, D_45, ab_10",
//                    "P_60, D_10, i_1",
                    "P_120, D_200, abcdefgh_2",
//                    "P_120, D_30, ab_6, c_1, P_500, ab_10",
            },
            10000,
            1,
            250,
            "graphics/Map/Map_01.png",
            "Farmland",
            "Good for beginners",
            "graphics/Map/Map_01_Thumbnail_Normal.png",
            "graphics/Map/Map_01_Thumbnail_Hover.png",
            InteractCode.LEVEL_A
    );


    public static final Level B = new Level(
            // Spline control points
            new Point2D[] {
                    new Point2D.Double(-58, 97),
                    new Point2D.Double(-16, 110),
                    new Point2D.Double(38, 138),
                    new Point2D.Double(96, 163),
                    new Point2D.Double(149, 183),
                    new Point2D.Double(190, 207),
                    new Point2D.Double(246, 218),
                    new Point2D.Double(304, 218),
                    new Point2D.Double(341, 189),
                    new Point2D.Double(338, 138),
                    new Point2D.Double(310, 93),
                    new Point2D.Double(269, 72),
                    new Point2D.Double(222, 79),
                    new Point2D.Double(199, 110),
                    new Point2D.Double(187, 153),
                    new Point2D.Double(184, 196),
                    new Point2D.Double(193, 248),
                    new Point2D.Double(216, 289),
                    new Point2D.Double(246, 325),
                    new Point2D.Double(287, 358),
                    new Point2D.Double(335, 384),
                    new Point2D.Double(385, 404),
                    new Point2D.Double(436, 409),
                    new Point2D.Double(492, 412),
                    new Point2D.Double(544, 413),
                    new Point2D.Double(596, 412),
                    new Point2D.Double(645, 397),
                    new Point2D.Double(684, 362),
                    new Point2D.Double(703, 322),
                    new Point2D.Double(710, 277),
                    new Point2D.Double(709, 229),
                    new Point2D.Double(690, 186),
                    new Point2D.Double(655, 166),
                    new Point2D.Double(612, 179),
                    new Point2D.Double(590, 211),
                    new Point2D.Double(575, 252),
                    new Point2D.Double(571, 289),
                    new Point2D.Double(578, 336),
                    new Point2D.Double(606, 377),
                    new Point2D.Double(649, 397),
                    new Point2D.Double(693, 397),
                    new Point2D.Double(753,401),
                    new Point2D.Double(816,394),
                    new Point2D.Double(870, 377),
                    new Point2D.Double(920, 358),
                    new Point2D.Double(975, 329),
                    new Point2D.Double(1013, 308),
            },

            // Enemy wave
            new String[] {
                    "P_60, D_45, ab_10",
                    "P_120, D_400, abcdefgh_2",
                    "P_120, D_30, ab_6, c_1, P_500, ab_10",
            },
            10000,
            75,
            200,
            "graphics/Map/Map_02.png",
            "Around and around",
            "Prepare for loops. Good for intermediate players.",
            "graphics/Map/Map_02_Thumbnail_Normal.png",
            "graphics/Map/Map_02_Thumbnail_Hover.png",
            InteractCode.LEVEL_B
    );

    private Level(Point2D[] pathSplineControls, String[] enemyWaves, int startMoney,
                  int startHealth, int waveEndMoney, String mapGraphicsPath,
                  String title, String description, String thumbnailNormalPath,
                  String thumbnailHoverPath, InteractCode interactCode
    ){
        this.pathSplineControls = pathSplineControls;
        this.enemyWaves = enemyWaves;
        this.startMoney = startMoney;
        this.waveEndMoney = waveEndMoney;
        this.startHealth = startHealth;
        setMapGraphics(mapGraphicsPath);
        this.levelTitle = title;
        this.levelDescription = description;
        this.thumbnailNormalPath = thumbnailNormalPath;
        this.thumbnailHoverPath = thumbnailHoverPath;
        this.interactCode = interactCode;
    }

    private void setMapGraphics(String path) {
        try {
            mapGraphics = ImageLoader.loadBufferedImage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected String getEnemyWave(int selectedWave) {
        return enemyWaves[selectedWave];
    }

    protected Point2D[] getPathSplineControls() {
        return pathSplineControls;
    }

    /**
     * @return maximum number of waves on the level
     */
    public int getMaxWaves() {
        return enemyWaves.length;
    }

    /**
     * @return initial money when loading the level
     */
    public int getStartMoney() {
        return startMoney;
    }

    /**
     * @return money earned after each wave
     */
    protected int getWaveEndMoney() {
        return waveEndMoney;
    }

    /**
     * @return background graphics of the level
     */
    protected BufferedImage getMapBackground() {
        return mapGraphics;
    }

    /**
     * @return initial health value upon laoding the level
     */
    public int getStartHealth() {
        return startHealth;
    }

    /**
     * @return the title of the level.
     */
    public String getTitle() {
        return levelTitle;
    }

    /**
     * @return description of the level
     */
    public String getLevelDescription() {
        return levelDescription;
    }

    /**
     * @return path to the level select button normal image
     */
    public String getThumbnailNormalPath() {
        return thumbnailNormalPath;
    }

    /**
     * @return path to the level sect button hover image
     */
    public String getThumbnailHoverPath() {
        return thumbnailHoverPath;
    }

    /**
     * @return interaction code to spawn the selected level
     */
    public InteractCode getInteractCode() {
        return interactCode;
    }
}
