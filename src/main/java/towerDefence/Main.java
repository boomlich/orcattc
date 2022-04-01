package towerDefence;

import towerDefence.model.GameModel;
import towerDefence.view.GameRender;
import towerDefence.view.GameRenderable;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GameRenderable gameModel = new GameModel();
        GameRender gameRender = new GameRender(gameModel);

        JFrame frame = new JFrame();
        frame.setContentPane(gameRender);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);

    }
}
