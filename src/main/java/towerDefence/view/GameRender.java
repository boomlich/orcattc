package towerDefence.view;

import towerDefence.model.GameMode;

import javax.swing.*;
import java.awt.*;


public class GameRender extends JPanel {

    private final gameCanvas gameCanvas;
    private final UICanvas gameUI;
    private final GraphicsDevice device;
    private final int gameWidth = 960;
    private final int gameHeight = 540;
    private final GameRenderable gameModel;

    private double scaleX;
    private double scaleY;

    public GameRender(GraphicsDevice device, GameRenderable gameModel){
        {
            this.setFocusable(true);
        }

        this.gameModel = gameModel;
        this.device = device;
        setRenderingScale();
        gameCanvas = new gameCanvas(gameModel, gameWidth, gameHeight);
        gameUI = new UICanvas(gameModel, gameWidth, gameHeight);
    }

    public UICanvas getGameUI() {
        return gameUI;
    }

    /**
     * Calculates the rendering x- and y-scale. Game width and height is fixed to
     * 960x540 (16:9 aspect ratio) resolution, but then scaled to match the display
     * resolution and dpi.
     */
    private void setRenderingScale() {
        double dpiScale = Toolkit.getDefaultToolkit().getScreenResolution() / 96.0;

        scaleX = ((float) device.getDisplayMode().getWidth() / (float) gameWidth) / dpiScale;
        scaleY = ((float) device.getDisplayMode().getHeight() / (float) gameHeight) / dpiScale;
    }

    /**
     * @return rendering scale in the x-dimension
     */
    public double getScaleX() {
        return scaleX;
    }

    /**
     * @return rendering scale in the y-dimension
     */
    public double getScaleY() {
        return scaleY;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.scale(getScaleX(), getScaleY());

        if (gameModel.getGameMode() != GameMode.MAIN_MENU) {
            gameCanvas.paint(g2D);
        }
        gameUI.paint(g2D);
    }
}
