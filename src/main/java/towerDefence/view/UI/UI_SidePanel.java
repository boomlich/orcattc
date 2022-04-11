package towerDefence.view.UI;

import towerDefence.view.GameRenderable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UI_SidePanel extends JPanel {

    GameRenderable gameModel;

    public UI_SidePanel(GameRenderable gameModel, int width, int height) {
        this.gameModel = gameModel;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.MAGENTA);

        int hPadding = width / 8;
        int componentWidth = width - 2 * hPadding;
        int champSelHeight = height / 4;
        int sTreeHeight = height / 5;

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, height/20));

        UI_ChampionSelect championSelect = new UI_ChampionSelect(gameModel, componentWidth, champSelHeight);
        this.add(championSelect);

        UI_SkillTree skillTree = new UI_SkillTree(gameModel, componentWidth, sTreeHeight);
        this.add(skillTree);
    }
}
