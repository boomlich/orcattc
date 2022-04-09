package towerDefence.view;

import towerDefence.enemies.IEnemy;
import towerDefence.level.path.PathPoint;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;

public interface GameRenderable {

    public void getBackground();

    /**
     *
     * @return HashMap of towers ordered by their z-depth value
     */
    public HashMap<Integer, List<ITower>> getTowers();

    /**
     *
     * @return HashMap of enemies ordered by their z-depth value
     */
    public HashMap<Integer, List<IEnemy>> getEnemies();

    public void getProjectiles();

    public void getParticles();

    public void getMoney();

    public List<PathPoint> getTrackPath();

    public Point2D[] getSplineControls();
}
