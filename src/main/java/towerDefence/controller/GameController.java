package towerDefence.controller;

import towerDefence.view.GameRender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    Timer timer;
    GameControllable gameModel;
    GameRender gameRender;

    public GameController(GameRender gameRender, GameControllable gameModel) {
        this.gameModel = gameModel;
        this.gameRender = gameRender;


        timer = new Timer(1 / gameModel.getFPS(), this);
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        gameModel.update();
        gameRender.repaint();
    }
}
