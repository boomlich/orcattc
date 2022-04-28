package towerDefence.view;

import towerDefence.model.GameMode;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;


public class GameRender extends JPanel {

    private final ICanvas boardCanvas;
    private final UICanvas gameUI;
    private final GraphicsDevice device;
    private final int gameWidth = 960;
    private final int gameHeight = 540;
    private final GameRenderable gameModel;

    private double scaleX;
    private double scaleY;

    private boolean fullscreen = true;


    public GameRender(GraphicsDevice device, GameRenderable gameModel){
        {
            this.setFocusable(true);
        }

        this.gameModel = gameModel;
        this.device = device;
        setScale();
        boardCanvas = new BoardCanvas(gameModel, gameWidth, gameHeight);
        gameUI = new UICanvas(gameModel, gameWidth, gameHeight);
    }

    public ICanvas getGameUI() {
        return gameUI;
    }

    private void setScale() {
        double dpiScale = Toolkit.getDefaultToolkit().getScreenResolution() / 96.0;
        if (fullscreen) {
            scaleX = ((float) device.getDisplayMode().getWidth() / (float) gameWidth) / dpiScale;
            scaleY = ((float) device.getDisplayMode().getHeight() / (float) gameHeight) / dpiScale;
        }
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

        if (gameModel.getGameMode() != GameMode.MAIN_MENU) {
            boardCanvas.paint(g2D);
        }
        gameUI.paint(g2D);
    }
}
