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
//        GraphicsConfiguration config = device.getDefaultConfiguration();
//        BufferedImage buffy = config.createCompatibleImage(device.getDisplayMode().getWidth(), device.getDisplayMode().getHeight(), Transparency.TRANSLUCENT);
//        Graphics g = buffy.getGraphics();

        // activate opengl
//        System.setProperty("sun.java2d.opengl", "true");

//        System.out.println(config);

        JFrame frame = new JFrame();

//        GraphicsDevice device = graphics.getScreenDevices()[1];

//        System.out.println(device.getDisplayMode());
        
        int width = device.getDisplayMode().getWidth();
        int height = device.getDisplayMode().getHeight();


        GameRenderable gameModel = new GameModel();
        GameRender gameRender = new GameRender(device, gameModel);
        GameController gameController = new GameController(gameRender, (GameControllable) gameModel);

        frame.setSize(new Dimension(width, height));


//        frame.setPreferredSize(new Dimension(1280, 720));
        frame.setContentPane(gameRender);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setVisible(true);
//        device.setFullScreenWindow(frame);

        System.out.println(device.getDisplayMode());

    }
}
