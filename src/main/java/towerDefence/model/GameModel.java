package towerDefence.model;

import towerDefence.components.collision.Collision;
import towerDefence.components.Weapons.Projectile;
import towerDefence.components.Targeting.TargetingMode;
import towerDefence.controller.GameControllable;
import towerDefence.enemies.IEnemy;
import towerDefence.level.LevelManager;
import towerDefence.level.Level;
import towerDefence.components.particles.Particle;
import towerDefence.tower.ITower;
import towerDefence.view.GameRenderable;
import UI.Interaction.InteractionManager;
import towerDefence.view.UICanvas;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class GameModel implements GameRenderable, GameControllable {

    private GameEntities gameEntities;
    private WaveSpawner waveSpawner;
    private LevelManager levelManager;
    private GameMode gameMode;
    private GameMode modePriorToPause;
    private ITower activeTower;
    private UICanvas uiCanvas;
    private boolean fastForward;
    private EconomyManager economyManager;
    private HealthManager healthManager;

    public GameModel() {
    }

    private void initiateGame(){
        loadMainMenu();
    }

    public void loadMainMenu() {
        if (gameEntities != null) {
            InteractionManager.clearInteractables();
        }
        changeGameMode(GameMode.MAIN_MENU);
    }

    @Override
    public void loadLevel(Level level) {
        activeTower = null;
        levelManager = new LevelManager();
        levelManager.loadLevel(level);
        healthManager = new HealthManager(level.getStartHealth());
        economyManager = new EconomyManager(level.getStartMoney());
        gameEntities = new GameEntities();
        gameEntities.addBoardCollisions(levelManager.getPath().getPathCollision());
        waveSpawner = new WaveSpawner(gameEntities);
        gameMode = GameMode.BUILD_PHASE;
        fastForward = false;
        uiCanvas.startNewLevel();
    }

    @Override
    public void startInvasionRound() {
        waveSpawner.setCurrentWave(levelManager.loadNextWave());
        changeGameMode(GameMode.INVASION_PHASE);
    }

    @Override
    public void addTower(ITower target) {
        if (economyManager.hasSufficientFunds(target.getCost(0))) {
            if (!isActiveTowerInSpawnMode()) {
                activeTower = target;
                activeTower.setGameEntities(gameEntities);
            }
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
    public GameMode getGameMode() {
        return gameMode;
    }

    @Override
    public void toggleFastForward() {
        fastForward = !fastForward;
        uiCanvas.toggleFastForward();
    }

    @Override
    public boolean isFastForwarding() {
        return fastForward;
    }

    @Override
    public void restartLevel() {
        if (activeTower != null) {
            activeTower = null;
        }
        InteractionManager.clearInteractables();
        loadLevel(levelManager.getCurrentLevel());
        uiCanvas.startNewLevel();
    }

    @Override
    public BufferedImage getMapGraphics() {
        return levelManager.getMapBackground();
    }

    @Override
    public void setGameUI(UICanvas uiCanvas) {
        this.uiCanvas = uiCanvas;
        initiateGame();
    }

    @Override
    public int getMaxWaves() {
        return levelManager.getMaxWaves();
    }

    @Override
    public int getCurrentWave() {
        return levelManager.getCurrentWaveNumber();
    }

    @Override
    public void selectTower(ITower tower) {
        if (gameMode == GameMode.INVASION_PHASE || gameMode == GameMode.BUILD_PHASE) {
            activeTower = tower;
        }
    }

    @Override
    public void placeTower() {
        if (activeTower.hasValidPlacement()) {
            if (economyManager.purchaseItem(activeTower.getCost(0))) {
                gameEntities.addTower(activeTower);
                activeTower.disableSpawnMode();
                activeTower = null;
            }
        }
    }

    @Override
    public void togglePauseGame() {
        if (gameMode == GameMode.PAUSE) {
            uiCanvas.togglePauseGame();
            changeGameMode(modePriorToPause);
        } else {
            modePriorToPause = gameMode;
            changeGameMode(GameMode.PAUSE);
            activeTower = null;
        }
    }

    @Override
    public ITower getActiveTower() {
        return activeTower;
    }

    @Override
    public void upgradeTower() {
        if (activeTower != null) {
            System.out.println(activeTower.getCost(1));
            if (economyManager.hasSufficientFunds(activeTower.getCost(1))) {
                economyManager.purchaseItem(activeTower.upgradeRank());
            }
        }
    }

    @Override
    public void sellTower() {
        if (activeTower != null) {
            economyManager.addMoney(activeTower.getSellValue());
            gameEntities.removeTower(activeTower);
            activeTower = null;
        }
    }

    @Override
    public void setTowerTargetingMode(TargetingMode targetingMode) {
        if (hasActiveTower()) {
            activeTower.setTargetingMode(targetingMode);
        }
    }

    @Override
    public void update(double deltaSteps) {

        if (gameMode != GameMode.MAIN_MENU) {

            if (hasActiveTower() && activeTower.activeSpawnMode()) {
                activeTower.update(deltaSteps);
            }
            if (gameMode != GameMode.PAUSE) {
                if (gameMode == GameMode.INVASION_PHASE) {
                    waveSpawner.update(deltaSteps);
                    economyManager.addMoney(gameEntities.getMoneyEarned());
                    if (!healthManager.reduceHealth(gameEntities.getDamageDone())) {
                        changeGameMode(GameMode.GAME_OVER);
                    }
                    endInvasionPhase();
                }
                gameEntities.update(deltaSteps);
            }
        }
    }

    private void endInvasionPhase() {
        if (isWaveDepleted()) {
            if (levelManager.getCurrentWaveNumber() == levelManager.getMaxWaves()) {
                changeGameMode(GameMode.WIN);
                if (activeTower != null) {
                    activeTower = null;
                }
            } else {
                changeGameMode(GameMode.BUILD_PHASE);
                economyManager.addMoney((int) (levelManager.getWaveEndMoney() * Math.pow(1.05, getCurrentWave())));
            }
        }
    }

    private void changeGameMode(GameMode gameMode) {
        if (gameMode == GameMode.BUILD_PHASE) {
            uiCanvas.startBuildPhase();
        } else if (gameMode == GameMode.INVASION_PHASE) {
            uiCanvas.startRound();
        } else if (gameMode == GameMode.WIN) {
            uiCanvas.displayWin();
        } else if (gameMode == GameMode.PAUSE) {
            uiCanvas.togglePauseGame();
        } else if (gameMode == GameMode.GAME_OVER) {
            uiCanvas.displayGameOver();
        } else if (gameMode == GameMode.MAIN_MENU) {
            uiCanvas.displayMainMenu();
        }
        this.gameMode = gameMode;
    }

    private boolean isWaveDepleted() {
        return gameEntities.getSortedEnemies(false).isEmpty() && waveSpawner.waveSpawnCompleted();
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
    public int getHealth() {
        return healthManager.getHealth();
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
    public List<Projectile> getProjectiles() {
        return gameEntities.getProjectiles();
    }

    @Override
    public List<Particle> getParticles() {
        return gameEntities.getParticles();
    }

    @Override
    public int getMoney() {
        return economyManager.getMoney();
    }

    @Override
    public boolean hasSufficiantFunds(int cost) {
        return economyManager.hasSufficientFunds(cost);
    }

    @Override
    public List<Collision> getPathCollision() {
        return levelManager.getPath().getPathCollision();
    }

}
