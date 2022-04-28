package towerDefence;

import towerDefence.controller.GameControllable;
import towerDefence.controller.GameController;
import towerDefence.model.GameModel;
import towerDefence.view.GameRender;
import towerDefence.view.GameRenderable;
import towerDefence.view.RenderingOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();

        int width = device.getDisplayMode().getWidth();
        int height = device.getDisplayMode().getHeight();

        GameRenderable gameModel = new GameModel();
        GameRender gameRender = new GameRender(device, gameModel);
        GameController gameController = new GameController(gameRender, (GameControllable) gameModel);

        JFrame frame = new JFrame();
        frame.setSize(new Dimension(width, height));
        frame.setContentPane(gameRender);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}
