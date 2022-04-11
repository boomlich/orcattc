package towerDefence.view;

import towerDefence.view.UI.*;

import java.awt.*;


public class UICanvas implements ICanvas {

    GameRenderable gameModel;
    UI_HUD HUD;

    UIContainer container;

    public UICanvas(GameRenderable gameModel, int width, int height) {

        container = new UIContainer( width, height);
        container.setLayoutManager(UILayout.VERTICAL);
        container.setPadding(new ContainerPadding(5));
        container.setBorder(new ContainerBorder(10));


        UIContainer test1 = new UIContainer(10,10);
        test1.setBackground(Color.ORANGE);
        test1.setAlignment(UIAlignment.SOUTH_EAST);
        container.add(test1);

        UIContainer test2 = new UIContainer(10,20);
        test2.setBackground(Color.MAGENTA);
        test2.setAlignment(UIAlignment.SOUTH_EAST);
        container.add(test2);

        UIContainer test3 = new UIContainer(500,250);
        test3.setBackground(Color.RED);
        test3.setAlignment(UIAlignment.SOUTH_EAST);
        container.add(test3);

        test3.setPadding(new ContainerPadding(10));
        test3.setLayoutManager(UILayout.VERTICAL);

        UITextBox testText1 = new UITextBox("Play");
        testText1.setAlignment(UIAlignment.NORTH);
        testText1.setSize(50);
        test3.add(testText1);

        UITextBox testText2 = new UITextBox("Welcome to the game");
        testText2.setAlignment(UIAlignment.NORTH);
        testText2.setSize(25);
        test3.add(testText2);



//        test3.add(new UITextBox("Yes"));


    }

    @Override
    public void paint(Graphics2D g2D) {
        container.paint(g2D);

        int size = 20;
        g2D.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
        g2D.drawString("TESTING123", 0, size);
    }
}
