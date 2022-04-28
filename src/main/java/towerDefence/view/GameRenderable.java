package towerDefence.view;

import towerDefence.components.Collision.Collision;
import towerDefence.components.Weapons.Projectile;
import towerDefence.enemies.IEnemy;
import towerDefence.level.path.PathPoint;
import towerDefence.model.GameMode;
import towerDefence.particles.Particle;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public interface GameRenderable {

    /**
     * @return health of the player
     */
    int getHealth();

    /**
     *
     * @return HashMap of towers ordered by their z-depth value
     */
    public HashMap<Integer, List<ITower>> getTowers();

    /**
     * @return Set of all active z-depth values for the current frame
     */
    public TreeSet<Integer> getZDepthRange();

    /**
     *
     * @return HashMap of enemies ordered by their z-depth value
     */
    public HashMap<Integer, List<IEnemy>> getEnemies();

    /**
     * @return list of all active projectiles
     */
    public List<Projectile> getProjectiles();

    /**
     * @return list of all active particles
     */
    List<Particle> getParticles();

    /**
     * @return currently available money for the player
     */
    int getMoney();

    /**
     * @param cost cost of the item
     * @return true if player has sufficient money to purchase the item
     */
    boolean hasSufficiantFunds(int cost);

    /**
     * @return currently selected tower
     */
    ITower getActiveTower();

    /**
     * @return
     */
    List<Collision> getPathCollision();

    /**
     * @return true if player has a tower selected
     */
    boolean hasActiveTower();

    /**
     * @return true if the player has a tower selected that is in spawning mode
     */
    boolean isActiveTowerInSpawnMode();

    /**
     * @return currently active gameMode
     */
    GameMode getGameMode();

    /**
     * @param uiCanvas connect the UI to the gameModel. After assigning the UI
     * the game will initiate
     */
    void setGameUI(UICanvas uiCanvas);

    /**
     * @return the maximum number of waves in the currently active level
     */
    int getMaxWaves();

    /**
     * @return the current wave number on the active level
     */
    int getCurrentWave();

    /**
     * @return true if fast forward is enabled
     */
    boolean isFastForwarding();

    /**
     * @return background graphics of the level
     */
    BufferedImage getMapGraphics();
}
