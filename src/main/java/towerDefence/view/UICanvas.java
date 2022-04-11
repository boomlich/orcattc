package towerDefence.view;

import towerDefence.view.UI.*;

import java.awt.*;


public class UICanvas implements ICanvas {

    GameRenderable gameModel;
    UI_HUD HUD;

    UIContainer container;

    public UICanvas(GameRenderable gameModel, int width, int height) {

        container = new UIContainer( width, height);
        container.setAlignment(UIAlignment.NORTH_WEST);
        container.setLayoutManager(UILayout.HORIZONTAL);
        container.setPadding(new ContainerPadding(5));
        container.setBorder(new ContainerBorder(10));


        UIContainer test1 = new UIContainer(10,10);
        test1.setBackground(Color.ORANGE);
        test1.setAlignment(UIAlignment.WEST);
        container.add(test1);

        UIContainer test2 = new UIContainer(10,20);
        test2.setBackground(Color.MAGENTA);
        test2.setAlignment(UIAlignment.WEST);
        container.add(test2);

        UIContainer test3 = new UIContainer(50,25);
        test3.setBackground(Color.RED);
        test3.setAlignment(UIAlignment.NORTH_WEST);
        container.add(test3);
    }

    @Override
    public void paint(Graphics2D g2D) {
        container.paint(g2D);
    }
}
