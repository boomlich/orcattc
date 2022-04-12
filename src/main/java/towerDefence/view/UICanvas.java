package towerDefence.view;

import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.UI_Champ;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class UICanvas implements ICanvas {

    private GameRenderable gameModel;

    private final UIContainer container;

    private final List<UIButton> interacable = new ArrayList<>();

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
        test3.setAlignment(UIAlignment.NORTH_EAST);
//        container.add(test3);

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

        test3.setBackgroundImage("TestSpriteSheet.png");

        UIButton testButton = new UIButton("Press");
        testButton.setAlignment(UIAlignment.CENTER);
        test3.add(testButton);

//        testButton.add(new UITextBox("Press"));


        UIContainer cont1 = new UIContainer(200, 100);
        cont1.setAlignment(UIAlignment.SOUTH_WEST);
        cont1.setBackground(Color.BLUE);

        UIContainer cont2 = new UIContainer(10, 50);
        cont2.setBackground(Color.GREEN);
        cont1.add(cont2);

        UIContainer cont3 = new UIContainer(50, 50);
        cont3.setBackground(Color.BLACK);
        cont2.add(cont3);

        UIContainer cont4 = new UIContainer(10, 20);
        cont4.setBackground(Color.cyan);
        cont3.add(cont4);
        test3.add(cont1);

        test3.setAlignment(UIAlignment.EAST);





//        test3.add(new UITextBox("Yes"));

        container.add(test3);


        UI_Champ championsSelect = new UI_Champ(360, 150);
        championsSelect.setAlignment(UIAlignment.SOUTH_WEST);
        container.add(championsSelect);




    }

    public void addInteractable(UIButton button) {
        interacable.add(button);
    }

    @Override
    public void paint(Graphics2D g2D) {
        container.paint(g2D);
    }
}
