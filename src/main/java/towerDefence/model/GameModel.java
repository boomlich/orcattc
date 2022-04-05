package towerDefence.model;

import towerDefence.controller.GameControllable;
import towerDefence.enemies.IEnemy;
import towerDefence.level.IGameLevel;
import towerDefence.level.LevelManager;
import towerDefence.level.levels.Level;
import towerDefence.level.path.PathPoint;
import towerDefence.tower.ITower;
import towerDefence.view.GameRenderable;

import java.awt.geom.Point2D;
import java.util.List;

public class GameModel implements GameRenderable, GameControllable {

    // The current step. Incremented every frame
    private GameEntities gameEntities;
    private WaveSpawner waveSpawner;
    private LevelManager levelManager;

    public GameModel() {
        loadLevel(Level.A);
        startRound();
    }


    @Override
    public void loadLevel(Level level) {
        levelManager = new LevelManager();
        levelManager.loadLevel(level);
        gameEntities = new GameEntities();
        waveSpawner = new WaveSpawner(gameEntities);
    }

    @Override
    public void startRound() {
        System.out.println("ROUND STARTED");
        waveSpawner.setCurrentWave(levelManager.getCurrentWave(0));
    }

    @Override
    public void addTower(ITower target) {

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

        gameEntities.update(deltaSteps);
        waveSpawner.update(deltaSteps);
    }

    @Override
    public int getFPS() {
        return 60;
    }


    @Override
    public void getBackground() {

    }

    @Override
    public void getTowers() {

    }

    @Override
    public List<IEnemy> getEnemies() {
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
    public Point2D[] getSplineControls() {
        return levelManager.getSplineControls();
    }
}
