package towerDefence.view;

import towerDefence.enemies.IEnemy;
import towerDefence.level.path.PathPoint;

import java.awt.geom.Point2D;
import java.util.List;

public interface GameRenderable {

    public void getBackground();

    public void getTowers();

    public List<IEnemy> getEnemies();

    public void getProjectiles();

    public void getParticles();

    public void getMoney();

    public List<PathPoint> getTrackPath();

    public Point2D[] getSplineControls();

}
