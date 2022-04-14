package towerDefence.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;


public class GameRender extends JPanel {

    private final ICanvas boardCanvas;
    private final ICanvas gameUI;
    private final GraphicsDevice device;
    private final int gameWidth = 960;
    private final int gameHeight = 540;

    private double scaleX;
    private double scaleY;


    public GameRender(GraphicsDevice device, GameRenderable gameModel){
        {
            this.setFocusable(true);
        }

        this.device = device;
        setScale();
        boardCanvas = new BoardCanvas(gameModel, gameWidth, gameHeight);
        gameUI = new UICanvas(gameModel, gameWidth, gameHeight);

    }

    private void setScale() {
        scaleX = (float) device.getDisplayMode().getWidth() / (float) gameWidth;
        scaleY = (float) device.getDisplayMode().getHeight() / (float) gameHeight;

    }

    public void changeResolution(GraphicsDevice device, int width, int height) {
        device.setDisplayMode(new DisplayMode(width, height, device.getDisplayMode().getBitDepth(), device.getDisplayMode().getRefreshRate()));
        setScale();
    }

    public double getScaleX() {
        return scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;


        g2D.scale(getScaleX(), getScaleY());


        boardCanvas.paint(g2D);
        gameUI.paint(g2D);

//        // Center lines
        g2D.draw(new Rectangle2D.Double(gameWidth / 2.0 - 2, 0, 4, gameHeight));
        g2D.draw(new Rectangle2D.Double(0, gameHeight / 2.0 -2, gameWidth, 4));

    }
}
