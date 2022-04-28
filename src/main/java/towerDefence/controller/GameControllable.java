package towerDefence.controller;

import towerDefence.components.Targeting.TargetingMode;
import towerDefence.level.Level;
import towerDefence.model.GameMode;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;

public interface GameControllable {

    /**
     * Clears everything and start a fresh level with the
     * level parameters. If any entities present in the game
     * it will be cleared and destoryed. This includes interactables.
     *
     * @param level level to be loaded
     */
    public void loadLevel(Level level);

    /**
     * Start spawning the next wave of enemies.
     */
    public void startInvasionRound();

    /**
     * If sufficient money funds to buy the target tower type,
     * spawn a new tower of the target type. Tower set to
     * spawning mode. While in spawn mode, the tower is not placed
     * on the map, but can be placed around and the purchase is not
     * confirmed.
     *
     * @param target tower to be added
     */
    public void addTower(ITower target);

    /**
     * Increase the rank of the currently selected tower.
     */
    public void upgradeTower();

    /**
     * Sell the currently active tower and refurbish a percentage
     * money spent on the tower.
     */
    public void sellTower();

    /**
     * Change the targeting mode. e.g. from first to strong on the
     * currently selected target.
     *
     * @param targetingMode selected targeting mode to be applied on the tower
     */
    public void setTowerTargetingMode(TargetingMode targetingMode);

    public void update(double deltaSteps);

    /**
     * @return number of frames per second
     */
    public int getFPS();

    /**
     * @param mousePosition
     */
    void updateMousePosition(Point2D mousePosition);

    /**
     *
     * @return true if the currently selected tower is in spawning mode
     */
    public boolean isActiveTowerInSpawnMode();

    /**
     * @param tower currently selected tower
     */
    public void selectTower(ITower tower);

    /**
     * If tower is on a valid position, complete the purchase transaction
     * of the tower and place on the selected position.
     */
    void placeTower();


    void togglePauseGame();

    /**
     * @return currently active gameMode
     */
    GameMode getGameMode();

    /**
     * Toggle the gameplay-speed multiplayer on and off, increase and
     * decreasing the speed of the entities on the map.
     */
    void toggleFastForward();

    /**
     *
     * @return true if fast forward enabled
     */
    boolean isFastForwarding();

    /**
     * Clear every game entity on the map, including all interactables.
     * Load the currently active level over again, setting the gameMode to
     * build-phase, clearing the currently active UI and activating the HUD.
     */
    void restartLevel();

    /**
     * Change the gameMode to Main Menu, clearing every entity, interacable
     * and level. Resetting the UI and enabling the Main Menu UI.
     */
    void loadMainMenu();
}
