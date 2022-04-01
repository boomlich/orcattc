package towerDefence.view;

import javax.swing.*;
import java.awt.*;


public class GameRender extends JComponent {

    ICanvas boardCanvas, UICanvas;

    public GameRender(GameRenderable gameModel){
        boardCanvas = new BoardCanvas(gameModel);
        UICanvas = new UICanvas(gameModel);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        // Antialiasing
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHints(rh);

        boardCanvas.paint(g2D);
        UICanvas.paint(g2D);
    }
}
