package towerDefence.level;

import towerDefence.level.levels.Level;
import towerDefence.level.path.SplinePath;
import towerDefence.level.path.SplinePathData;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class LevelManager implements IGameLevel {

    private SplinePath path;
    private Level currentLevel;
    private int maxWaveNumber;
    private int currentWaveNumber = 0;

    public LevelManager() {
    }

    public void loadLevel(Level selectedLevel) {
        currentLevel = selectedLevel;
        maxWaveNumber = currentLevel.getMaxWaves();
        path = new SplinePath(currentLevel.getPathSplineControls());
        currentWaveNumber = 0;
    }

    @Override
    public BufferedImage getMapBackground() {
        return currentLevel.getMapBackground();
    }

    @Override
    public Point2D[] getSplineControls() {
        return path.getSplineControls();
    }

    @Override
    public SplinePathData getPath() {
        return path.getSplinePathData();
    }

    @Override
    public EnemyWave getCurrentWave(int waveNumber) {
        return new EnemyWave(currentLevel.getEnemyWave(waveNumber), getPath());
    }

    public EnemyWave loadNextWave() {
        return new EnemyWave(currentLevel.getEnemyWave(currentWaveNumber++), getPath());
    }

    @Override
    public int getMaxWaves() {
        return maxWaveNumber;
    }

    @Override
    public int getCurrentWaveNumber() {
        return currentWaveNumber;
    }

    @Override
    public int getWaveEndMoney() {
        return currentLevel.getWaveEndMoney();
    }
}
