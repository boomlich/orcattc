package towerDefence.view.UI;

import towerDefence.view.GameRenderable;

import javax.swing.*;
import java.awt.*;

public class UI_SkillTree extends JPanel {

    GameRenderable gameModel;


    public UI_SkillTree(GameRenderable gameModel, int width, int height) {
            this.gameModel = gameModel;
            this.setPreferredSize(new Dimension(width, height));
            this.setBackground(Color.BLUE);
    }

}
