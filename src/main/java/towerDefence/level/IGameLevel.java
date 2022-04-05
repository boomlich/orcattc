package towerDefence.level;

import towerDefence.level.path.PathPoint;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.List;

public interface IGameLevel {

    public BufferedImage getBackground();

    public Point2D[] getSplineControls();

    public List<PathPoint> getPath();

    public EnemyWave getCurrentWave(int waveNumber);

}
