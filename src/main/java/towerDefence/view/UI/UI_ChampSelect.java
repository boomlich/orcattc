package towerDefence.view.UI;

import towerDefence.view.GameRenderable;

import javax.swing.*;
import java.awt.*;

public class UI_ChampSelect extends JPanel {

    GameRenderable gameModel;

    public UI_ChampSelect (GameRenderable gameModel, int width, int height){
        this.gameModel = gameModel;
//        this.setBounds(0, 0, width, height);

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.darkGray);
    }
}
