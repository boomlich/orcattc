package towerDefence.view;

import java.awt.*;

public interface ICanvas {

    public void paint(Graphics2D g2D);

    void update(double deltaSteps);

    void pauseGame();
}
