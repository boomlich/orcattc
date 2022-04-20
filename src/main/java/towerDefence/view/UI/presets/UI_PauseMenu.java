package towerDefence.view.UI.presets;


import towerDefence.view.GameRenderable;
import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.buttons.UI_Button_MenuButton;

import java.awt.*;

public class UI_PauseMenu extends UIContainer {

    GameRenderable gameModel;

    public UI_PauseMenu(int width, int height, GameRenderable gameModel) {
        super(width, height);
        this.gameModel = gameModel;

        this.setLayoutManager(UILayout.VERTICAL);
        this.setPadding(new ContainerPadding(0, 10));

        UITextBox title = new UITextBox("Game Paused", 20);

        UIContainer topContainter = new UIContainer(125, 128);
        topContainter.setBackground(Color.RED);
        topContainter.setPadding(new ContainerPadding(5));
        topContainter.setLayoutManager(UILayout.VERTICAL);

        topContainter.add(new UI_Button_MenuButton("Resume", 15));
        topContainter.add(new UI_Button_MenuButton("Restart", 15));
        topContainter.add(new UI_Button_MenuButton("Main menu", 15));

        UIContainer bottomContainer = new UIContainer(400, 128);
        bottomContainer.setPadding(new ContainerPadding(10));
        bottomContainer.setBackground(Color.GREEN);

        UIContainer optionsPane = new UIContainer(150, 128);
        optionsPane.setBackground(Color.ORANGE);
        optionsPane.setBorder(new ContainerBorder(5));

        UITextBox optionsTitle = new UITextBox("Options", 15);
        optionsTitle.setAlignment(UIAlignment.NORTH);
        optionsPane.add(optionsTitle);


        UIContainer debugPane = new UIContainer(150, 128);
        debugPane.setBackground(Color.BLUE);
        debugPane.setBorder(new ContainerBorder(5));

        UITextBox debugTitle = new UITextBox("Debug", 15);
        debugTitle.setAlignment(UIAlignment.NORTH);
        debugPane.add(debugTitle);

        bottomContainer.add(optionsPane);
        bottomContainer.add(debugPane);


        this.add(title);
        this.add(topContainter);
        this.add(bottomContainer);
    }
}
