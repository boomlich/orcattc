package towerDefence.view;

import towerDefence.enemies.IEnemy;
import towerDefence.level.path.PathPoint;
import towerDefence.tower.ITower;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;


public class GameRender extends JPanel {

    ICanvas boardCanvas;
    ICanvas gameUI;


    public GameRender(GameRenderable gameModel, int width, int height){
        {
            this.setFocusable(true);
        }

        boardCanvas = new BoardCanvas(gameModel, width, height);
        gameUI = new UICanvas(gameModel, width, height);


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        boardCanvas.paint(g2D);
        gameUI.paint(g2D);

        // Center lines
        g2D.draw(new Rectangle2D.Double(getWidth()/2 - 2, 0, 4, getHeight()));
        g2D.draw(new Rectangle2D.Double(0, getHeight() / 2 -2, getWidth(), 4));

    }
}
