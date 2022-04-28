package towerDefence.view;

import java.awt.*;

public interface ICanvas {

    public void paint(Graphics2D g2D);

    void update(double deltaSteps);

    void togglePauseGame();

    void startRound();

    void addTowerMenu();

    void displayWin();

    void displayGameOver();

    void displayLevelSelect();
}
