package towerDefence.view.UI.presets;


import towerDefence.view.GameRenderable;
import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.buttons.UI_Button_MenuButton;

import java.awt.*;

public class UI_MainMenu extends UIContainer {

    GameRenderable gameModel;

    public UI_MainMenu(int width, int height) {
        super(width, height);
        this.setLayoutManager(UILayout.VERTICAL);


        UIContainer title = new UIContainer(300, 100);
        title.setBackground(Color.GREEN);

        UIContainer menuContainer = new UIContainer(158, 100);
        menuContainer.setBackground(Color.orange);
        menuContainer.setLayoutManager(UILayout.VERTICAL);
        menuContainer.setPadding(new ContainerPadding(5));

        UIButton play = new UI_Button_MenuButton("Play", 15, InteractCode.PLAY_GAME);
        UIButton quit = new UI_Button_MenuButton("QUIT GAME", 15, InteractCode.QUIT);

        menuContainer.add(play);
        menuContainer.add(quit);

        this.add(title);
        this.add(menuContainer);
    }
}
