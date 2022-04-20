package towerDefence.controller;

import towerDefence.view.GameRender;

import javax.swing.*;
import java.awt.event.*;

public class GameController implements ActionListener, KeyListener {
    Timer timer;
    GameControllable gameModel;
    GameRender gameRender;
    MouseController mouseController;

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

        double detaSteps = 1;

        gameModel.update(detaSteps);
        gameRender.repaint();
        gameRender.getGameUI().update(detaSteps);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE){
            gameModel.togglePauseGame();
            gameRender.getGameUI().pauseGame();
        } else if (key == KeyEvent.VK_Q) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



}
