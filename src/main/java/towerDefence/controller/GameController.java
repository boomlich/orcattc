package towerDefence.controller;

import towerDefence.view.GameRender;
import towerDefence.view.RenderingOptions;

import javax.swing.*;
import java.awt.event.*;

public class GameController implements ActionListener, KeyListener {
    private Timer timer;
    private GameControllable gameModel;
    private GameRender gameRender;
    private MouseController mouseController;

    private double timeMultiplier = 1.0;

    public GameController(GameRender gameRender, GameControllable gameModel) {
        this.gameModel = gameModel;
        this.gameRender = gameRender;

        mouseController = new MouseController(gameRender, gameModel, this);

        gameRender.addKeyListener(this);
        gameRender.addMouseMotionListener(mouseController);
        gameRender.addMouseListener(mouseController);

        timer = new Timer(1 / gameModel.getFPS(), this);
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        double deltaSteps = 1 * timeMultiplier;

        gameModel.update(deltaSteps);
        gameRender.repaint();
        gameRender.getGameUI().update(deltaSteps);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE){
            gameModel.togglePauseGame();
            gameRender.getGameUI().togglePauseGame();
        } else if (key == KeyEvent.VK_Q) {
            System.exit(0);
        } else if (key == KeyEvent.VK_1) {
            RenderingOptions.DEBUG = !RenderingOptions.DEBUG;
        } else if (key == KeyEvent.VK_2) {
            RenderingOptions.ENEMY_HEALTH = !RenderingOptions.ENEMY_HEALTH;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public void toggleFastForward() {
        if (timeMultiplier != 1.0) {
            timeMultiplier = 1.0;
        } else {
            timeMultiplier = 2.0;
        }
    }
}
