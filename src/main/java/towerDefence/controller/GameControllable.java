package towerDefence.controller;

import towerDefence.tower.ITower;

public interface GameControllable {

    public void loadLevel();

    public void startRound();

    public void addTower(ITower target);

    public void upgradeTower(ITower target);

    public void sellTower(ITower target);

    public void update();

    /**
     * @return number of frames per second
     */
    public int getFPS();
}
