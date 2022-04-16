package towerDefence.model;

import towerDefence.components.Collision;
import towerDefence.controller.GameControllable;
import towerDefence.enemies.IEnemy;
import towerDefence.level.IGameLevel;
import towerDefence.level.LevelManager;
import towerDefence.level.levels.Level;
import towerDefence.level.path.PathPoint;
import towerDefence.tower.ITower;
import towerDefence.view.GameRenderable;
import towerDefence.view.Interaction.Interactable;
import towerDefence.view.Interaction.InteractionManager;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class GameModel implements GameRenderable, GameControllable {

    // The current step. Incremented every frame
    private GameEntities gameEntities;
    private WaveSpawner waveSpawner;
    private LevelManager levelManager;
    private int waveNumber;
    private GameMode gameMode;
    private ITower activeTower;

    public GameModel() {
        loadLevel(Level.A);
    }

    @Override
    public void loadLevel(Level level) {
        levelManager = new LevelManager();
        levelManager.loadLevel(level);
        gameEntities = new GameEntities();
        gameEntities.addBoardCollisions(levelManager.getPath().getPathCollision());
        waveSpawner = new WaveSpawner(gameEntities);
        waveNumber = 0;
        gameMode = GameMode.BUILD_PHASE;
    }

    @Override
    public void startRound() {
        System.out.println("ROUND STARTED");
        waveSpawner.setCurrentWave(levelManager.getCurrentWave(waveNumber));
        waveNumber ++;
        gameMode = GameMode.INVASION_PHASE;
    }

    @Override
    public void addTower(ITower target) {

        if (!isActiveTowerInSpawnMode()) {
            activeTower = target;
            activeTower.setGameEntities(gameEntities);
        }
    }

    @Override
    public boolean hasActiveTower() {
        return activeTower != null;
    }

    @Override
    public boolean isActiveTowerInSpawnMode() {
        if (hasActiveTower()) {
            return activeTower.activeSpawnMode();
        }
        return false;
    }

    @Override
    public void selectTower(ITower tower) {
        activeTower = tower;
    }

    @Override
    public void placeTower() {
        if (activeTower.hasValidPlacement()) {
            gameEntities.addTower(activeTower);
            activeTower.disableSpawnMode();
            activeTower = null;
        } else {
            System.out.println("INVALID PLACEMENT");
        }
    }

    @Override
    public ITower getActiveTower() {
        return activeTower;
    }

    @Override
    public void upgradeTower(ITower target) {

    }

    @Override
    public void sellTower(ITower target) {

    }

    @Override
    public void update() {
        double deltaSteps = 1;

        if (hasActiveTower() && activeTower.activeSpawnMode()) {
            activeTower.update(deltaSteps);
        }

        if (gameMode == GameMode.INVASION_PHASE) {
            gameEntities.update(deltaSteps);
            waveSpawner.update(deltaSteps);
        }
    }

    @Override
    public int getFPS() {
        return 60;
    }


    @Override
    public void updateMousePosition(Point2D mousePosition) {
        if (hasActiveTower() && activeTower.activeSpawnMode()) {
            activeTower.updatePosition(mousePosition);
        }
    }


    @Override
    public void getBackground() {

    }

    @Override
    public HashMap<Integer, List<ITower>> getTowers() {
        return gameEntities.getTowers();
    }

    @Override
    public TreeSet<Integer> getZDepthRange() {
        return gameEntities.getzDepthRange();
    }

    @Override
    public HashMap<Integer, List<IEnemy>> getEnemies() {
        return gameEntities.getEnemies();
    }

    @Override
    public void getProjectiles() {

    }

    @Override
    public void getParticles() {

    }

    @Override
    public void getMoney() {

    }

    @Override
    public List<PathPoint> getTrackPath() {
        return levelManager.getPath().getPathPoints();
    }

    @Override
    public List<Collision> getPathCollision() {
        return levelManager.getPath().getPathCollision();
    }

    @Override
    public Point2D[] getSplineControls() {
        return levelManager.getSplineControls();
    }
}
