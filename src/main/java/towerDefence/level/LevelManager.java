package towerDefence.level;

import towerDefence.level.path.SplinePath;
import towerDefence.level.path.SplinePathData;

import java.awt.image.BufferedImage;

public class LevelManager implements ILevelManager {

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
    public SplinePathData getPath() {
        return path.getSplinePathData();
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

    @Override
    public Level getCurrentLevel() {
        return currentLevel;
    }
}
