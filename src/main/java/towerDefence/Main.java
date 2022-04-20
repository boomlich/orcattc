package towerDefence;

import towerDefence.controller.GameControllable;
import towerDefence.controller.GameController;
import towerDefence.model.GameModel;
import towerDefence.view.GameRender;
import towerDefence.view.GameRenderable;
import towerDefence.view.RenderingOptions;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();

        JFrame frame = new JFrame();

//        GraphicsDevice device = graphics.getScreenDevices()[1];

//        System.out.println(device.getDisplayMode());



//        int width = device.getDisplayMode().getWidth();
//        int height = device.getDisplayMode().getHeight();
//
//        width = 1280;
//        height = 720;


        GameRenderable gameModel = new GameModel();
        GameRender gameRender = new GameRender(device, gameModel);
        GameController gameController = new GameController(gameRender, (GameControllable) gameModel);


//        frame.setSize(new Dimension(width, height));
        System.out.println();




//        frame.setPreferredSize(new Dimension(1280, 720));
        frame.setContentPane(gameRender);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setVisible(true);

        device.setFullScreenWindow(frame);

//        DisplayMode testMode = new DisplayMode(1280, 720, device.getDisplayMode().getBitDepth(), device.getDisplayMode().getRefreshRate());
//        device.setDisplayMode(testMode);


//        for (DisplayMode dm: device.getDisplayModes()) {
//            System.out.println(dm);
//        }

    }
}
