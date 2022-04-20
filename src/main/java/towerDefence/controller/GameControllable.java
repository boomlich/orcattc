package towerDefence.controller;

import towerDefence.components.TargetingMode;
import towerDefence.level.levels.Level;
import towerDefence.tower.ITower;
import towerDefence.view.Interaction.InteractCode;

import java.awt.geom.Point2D;

public interface GameControllable {

    public void loadLevel(Level level);

    public void startRound();

    public void addTower(ITower target);

    public void upgradeTower();

    public void sellTower(ITower target);

    public void setTowerTargetingMode(TargetingMode targetingMode);

    public void update(double deltaSteps);

    /**
     * @return number of frames per second
     */
    public int getFPS();

    public void updateMousePosition(Point2D mousePosition);

    public boolean isActiveTowerInSpawnMode();

    public void selectTower(ITower tower);

    public void placeTower();
}
