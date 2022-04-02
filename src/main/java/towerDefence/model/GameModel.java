package towerDefence.model;

import towerDefence.controller.GameControllable;
import towerDefence.level.IGameMap;
import towerDefence.level.MapA;
import towerDefence.tower.ITower;
import towerDefence.view.GameRenderable;

public class GameModel implements GameRenderable, GameControllable {

    // The current step. Incremented every frame
    private long steps;
    private GameEntities gameEntities;
    private WaveSpawner waveSpawner;
    private IGameMap gameMap;

    public GameModel() {
        steps = 0;
        loadLevel();
        startRound();
    }


    @Override
    public void loadLevel() {
        gameMap = new MapA();
        gameEntities = new GameEntities();
        waveSpawner = new WaveSpawner(gameEntities);
    }

    @Override
    public void startRound() {

        System.out.println("ROUND STARTED");
        waveSpawner.setCurrentWave(gameMap.getCurrentWave());
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
    public void getEnemies() {

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
}
