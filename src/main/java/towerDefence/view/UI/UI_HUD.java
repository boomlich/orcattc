package towerDefence.view.UI;

import towerDefence.view.GameRenderable;

import javax.swing.*;
import java.awt.*;

public class UI_HUD extends JPanel {

    GameRenderable gameModel;

    public UI_HUD(GameRenderable gameModel, int width, int height) {
        this.gameModel = gameModel;
        this.setBackground(Color.ORANGE);


        this.setLayout(new BorderLayout());
        
        Dimension containerDimension = new Dimension(width / 3, height);



        JPanel leftContainer = new JPanel();
        leftContainer.setPreferredSize(containerDimension);
        leftContainer.setBackground(Color.GREEN);
        leftContainer.setBorder(BorderFactory.createEmptyBorder(25, 30, 0, 30));
        leftContainer.setLayout(new BorderLayout());
        this.add(leftContainer, BorderLayout.WEST);

        JPanel bottomLeft = new JPanel();
        bottomLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        bottomLeft.setPreferredSize(new Dimension(100, 150));
        bottomLeft.setBackground(Color.ORANGE);
        leftContainer.add(bottomLeft, BorderLayout.SOUTH);

        JPanel topLeft = new JPanel();
        topLeft.setPreferredSize(new Dimension(100, 150));
        topLeft.setBackground(Color.PINK);
        leftContainer.add(topLeft, BorderLayout.NORTH);

        UI_ChampSelect champSelect = new UI_ChampSelect(gameModel, width / 5, 50);
        bottomLeft.add(champSelect);



        JPanel rightContainer = new JPanel();
        rightContainer.setPreferredSize(containerDimension);
        rightContainer.setBackground(Color.RED);
        this.add(rightContainer, BorderLayout.EAST);

        JPanel centerContainer = new JPanel();
        centerContainer.setPreferredSize(containerDimension);
        centerContainer.setBackground(Color.MAGENTA);
        this.add(centerContainer);






//        // Add side-panel
//        UI_SidePanel sidePanel = new UI_SidePanel(gameModel, width/5, height);
//        this.add(sidePanel, BorderLayout.EAST);
//
//        // In order to not overlap the side-panel, we first make a left column that is centered
//        // and put the status-bar at the bottom of the left column
//        JPanel leftColumn = new JPanel();
//        leftColumn.setLayout(new BorderLayout());
//        this.add(leftColumn);
//
//        // Add Status bar
//        UI_StatusBar statusBar = new UI_StatusBar(gameModel, 100, height/15);
//        leftColumn.add(statusBar, BorderLayout.SOUTH);

    }

//    private JPanel[] horizontalPartitions(int partitions, int width, int height) {
//        JPanel[] screenPartitions = new JPanel[partitions];
//
//        for (int i = 0; i < partitions; i++) {
//            JPanel panel = new JPanel();
//            panel.setPreferredSize(new Dimension(width / partitions, height));
//            screenPartitions[i] =
//
//        }
//
//
//        return null;
//    }
}