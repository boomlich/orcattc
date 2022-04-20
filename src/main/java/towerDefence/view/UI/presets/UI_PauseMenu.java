package towerDefence.view.UI.presets;


import towerDefence.view.GameRenderable;
import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.buttons.UI_Button_Pause_Resume;

import java.awt.*;

public class UI_PauseMenu extends UIContainer {

    GameRenderable gameModel;

    public UI_PauseMenu(int width, int height, GameRenderable gameModel) {
        super(width, height);
        this.gameModel = gameModel;

        this.setLayoutManager(UILayout.VERTICAL);
        this.setAlignment(UIAlignment.CENTER);
        this.setPadding(new ContainerPadding(0, 10));

        UITextBox title = new UITextBox("Game Paused", 20);
        title.setAlignment(UIAlignment.CENTER);

        UIContainer topContainter = new UIContainer(125, 128);
        topContainter.setBackground(Color.RED);
        topContainter.setAlignment(UIAlignment.CENTER);
        topContainter.setLayoutManager(UILayout.VERTICAL);

        topContainter.add(new UI_Button_Pause_Resume());


        UIContainer bottomContainer = new UIContainer(400, 128);
        bottomContainer.setBackground(Color.GREEN);
        bottomContainer.setAlignment(UIAlignment.CENTER);

        this.add(title);
        this.add(topContainter);
        this.add(bottomContainer);
    }
}
