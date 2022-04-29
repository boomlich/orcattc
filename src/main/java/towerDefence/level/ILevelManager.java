package towerDefence.level;

import towerDefence.level.path.SplinePathData;

import java.awt.image.BufferedImage;

public interface ILevelManager {

    /**
     * @return map background graphics
     */
    BufferedImage getMapBackground();

    /**
     *
     * @return current level's enemy movement path
     */
    SplinePathData getPath();

    /**
     * @return the current level's maximum waves
     */
    int getMaxWaves();

    /**
     * @return current levels wave number
     */
    int getCurrentWaveNumber();

    /**
     * @return the current level's end of round money price
     */
    int getWaveEndMoney();

    /**
     * @return the currently active level
     */
    Level getCurrentLevel();
}
