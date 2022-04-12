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


        GameRenderable gameModel = new GameModel();
        GameRender gameRender = new GameRender(gameModel, width, height);
        GameController gameController = new GameController(gameRender, (GameControllable) gameModel);
//        UICanvas uiCanvas = new UICanvas(gameModel, width, height);

        JFrame frame = new JFrame();
        frame.setContentPane(gameRender);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setVisible(true);



        device.setFullScreenWindow(frame);
        System.out.println(device.getDisplayMode());

//        DisplayMode testMode = new DisplayMode(1280, 720, device.getDisplayMode().getBitDepth(), device.getDisplayMode().getRefreshRate());
//        device.setDisplayMode(testMode);

//        device.getDisplayModes();

//        System.out.println("RESOLUTION:    " + Toolkit.getDefaultToolkit().getScreenResolution());

    }
}
