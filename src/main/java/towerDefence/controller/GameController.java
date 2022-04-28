package towerDefence.controller;

import towerDefence.model.GameMode;
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

        mouseController = new MouseController(gameRender, gameModel);

        gameRender.addKeyListener(this);
        gameRender.addMouseMotionListener(mouseController);
        gameRender.addMouseListener(mouseController);

        timer = new Timer(1 / gameModel.getFPS(), this);
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        double deltaSteps;
        if (gameModel.getGameMode() == GameMode.INVASION_PHASE) {
            deltaSteps = 1 * getTimeMultiplier();
        } else {
            deltaSteps = 1;
        }


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
            if (gameModel.getGameMode() != GameMode.MAIN_MENU) {
                gameModel.togglePauseGame();
            }
        } else if (key == KeyEvent.VK_Q) {
            System.exit(0);
        } else if (key == KeyEvent.VK_1) {
            RenderingOptions.DEBUG = !RenderingOptions.DEBUG;
        } else if (key == KeyEvent.VK_2) {
            RenderingOptions.ENEMY_HEALTH = !RenderingOptions.ENEMY_HEALTH;
        } else if (key == KeyEvent.VK_SPACE) {
            if (gameModel.getGameMode() != GameMode.MAIN_MENU) {
                if (gameModel.getGameMode() == GameMode.BUILD_PHASE) {
                    gameModel.startInvasionRound();
                } else {
                    gameModel.toggleFastForward();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public double getTimeMultiplier() {
        if (gameModel.isFastForwarding()) {
            return 2.0;
        } else {
            return 1.0;
        }
    }
}
