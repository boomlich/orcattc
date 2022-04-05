package towerDefence.level;

import towerDefence.level.levels.Level;
import towerDefence.level.path.PathPoint;
import towerDefence.level.path.SplinePath;
import towerDefence.level.path.SplinePathData;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class LevelManager implements IGameLevel {

    private SplinePath path;
    private Level currentLevel;

//    public LevelManager(Point2D[] pathSplineControl, String enemyWaves) {
//        path = new TrackPath(pathSpineControls);
//    }

    public LevelManager() {
    }

    public void loadLevel(Level selectedLevel) {
        currentLevel = selectedLevel;
        path = new SplinePath(currentLevel.getPathSplineControls());
    }

    @Override
    public BufferedImage getBackground() {
        return null;
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
}
