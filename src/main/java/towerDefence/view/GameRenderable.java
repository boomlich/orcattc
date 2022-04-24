package towerDefence.view;

import towerDefence.components.Collision;
import towerDefence.components.Projectile;
import towerDefence.enemies.IEnemy;
import towerDefence.level.path.PathPoint;
import towerDefence.model.GameMode;
import towerDefence.particles.Particle;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public interface GameRenderable {

    public void getBackground();

    /**
     *
     * @return HashMap of towers ordered by their z-depth value
     */
    public HashMap<Integer, List<ITower>> getTowers();

    public TreeSet<Integer> getZDepthRange();

    /**
     *
     * @return HashMap of enemies ordered by their z-depth value
     */
    public HashMap<Integer, List<IEnemy>> getEnemies();

    public List<Projectile> getProjectiles();

    public List<Particle> getParticles();

    public void getMoney();

    public List<PathPoint> getTrackPath();

    public Point2D[] getSplineControls();

    public ITower getActiveTower();

    public List<Collision> getPathCollision();

    public boolean hasActiveTower();

    public boolean isActiveTowerInSpawnMode();

    GameMode getGameMode();

    int getMaxWaves();

    int getCurrentWave();
}
