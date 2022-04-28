package towerDefence.level;

import towerDefence.level.path.SplinePathData;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public interface IGameLevel {

    public BufferedImage getMapBackground();

    public Point2D[] getSplineControls();

    public SplinePathData getPath();

    public EnemyWave getCurrentWave(int waveNumber);

    public EnemyWave loadNextWave();

    int getMaxWaves();

    int getCurrentWaveNumber();

    int getWaveEndMoney();

    Level getcurrentLevel();
}
