package towerDefence.controller;

import towerDefence.view.GameRender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements ActionListener, KeyListener {
    Timer timer;
    GameControllable gameModel;
    GameRender gameRender;

    public GameController(GameRender gameRender, GameControllable gameModel) {
        this.gameModel = gameModel;
        this.gameRender = gameRender;

        gameRender.addKeyListener(this);

        timer = new Timer(1 / gameModel.getFPS(), this);
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        gameModel.update();
        gameRender.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
