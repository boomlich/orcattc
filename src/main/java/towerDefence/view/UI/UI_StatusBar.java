package towerDefence.view.UI;

import towerDefence.view.GameRenderable;

import javax.swing.*;
import java.awt.*;

public class UI_StatusBar extends JPanel {

    GameRenderable gameModel;

    public UI_StatusBar(GameRenderable gameModel, int width, int height) {
        this.gameModel = gameModel;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.ORANGE);


    }


}
