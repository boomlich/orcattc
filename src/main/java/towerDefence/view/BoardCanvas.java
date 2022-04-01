package towerDefence.view;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BoardCanvas implements ICanvas{

    GameRenderable gameModel;

    public BoardCanvas(GameRenderable gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void paint(Graphics2D g2D) {

        g2D.fill(new Rectangle2D.Double(10, 10, 50, 50));

    }
}