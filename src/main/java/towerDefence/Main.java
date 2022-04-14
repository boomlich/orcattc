package towerDefence;

import towerDefence.controller.GameControllable;
import towerDefence.controller.GameController;
import towerDefence.model.GameModel;
import towerDefence.view.GameRender;
import towerDefence.view.GameRenderable;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphics.getDefaultScreenDevice();


        int width = device.getDisplayMode().getWidth();
        int height = device.getDisplayMode().getHeight();
//
//        width = 960;
//        height = 540;


        GameRenderable gameModel = new GameModel();
        GameRender gameRender = new GameRender(device, gameModel);
        GameController gameController = new GameController(gameRender, (GameControllable) gameModel);

        JFrame frame = new JFrame();
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
