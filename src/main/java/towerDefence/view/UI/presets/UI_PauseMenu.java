package towerDefence.view.UI.presets;


import towerDefence.view.GameRenderable;
import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.buttons.UI_Button_MenuButton;

import java.awt.*;

public class UI_PauseMenu extends UIContainer {

    GameRenderable gameModel;

    public UI_PauseMenu(int width, int height, GameRenderable gameModel) {
        super(width, height);
        this.setBackground(new Color(0, 0, 0, 125));

        int endWidth = 12;
        int frameHeight = 186;

        UIContainer frameLeft = new UIContainer(endWidth, frameHeight);
        frameLeft.setBackgroundImage("graphics/UI/Pause/UI_Pause_Frame_Left.png");

        UIContainer frameRight = new UIContainer(endWidth, frameHeight);
        frameRight.setBackgroundImage("graphics/UI/Pause/UI_Pause_Frame_Right.png");

        UIContainer frameMain = new UIContainer(158 - 2 * endWidth, frameHeight);
        frameMain.setBackgroundImage("graphics/UI/Pause/UI_Pause_Frame_Main.png");
        frameMain.setLayoutManager(UILayout.VERTICAL);


        UITextBox title = new UITextBox("GAME PAUSED", 20);

        UIButton resume = new UI_Button_MenuButton("Resume", 15, InteractCode.PAUSE);
        UIButton restart = new UI_Button_MenuButton("Restart", 15, InteractCode.RESTART);
        UIButton mainMenu = new UI_Button_MenuButton("Main Menu", 15, InteractCode.MAIN_MENU);

        frameMain.setPadding(new ContainerPadding(10));
        frameMain.add(title);
        frameMain.add(resume);
        frameMain.add(restart);
        frameMain.add(mainMenu);



        this.add(frameLeft);
        this.add(frameMain);
        this.add(frameRight);







//        this.gameModel = gameModel;
//        this.setBackground(new Color(0, 0, 0, 125));
//
//        this.setLayoutManager(UILayout.VERTICAL);
//        this.setPadding(new ContainerPadding(0, 10));
//
//        UITextBox title = new UITextBox("Game Paused", 20);
//
//        UIContainer topContainter = new UIContainer(125, 128);
//        topContainter.setBackground(Color.RED);
//        topContainter.setPadding(new ContainerPadding(5));
//        topContainter.setLayoutManager(UILayout.VERTICAL);
//
//        topContainter.add(new UI_Button_MenuButton("Resume", 15));
//        topContainter.add(new UI_Button_MenuButton("Restart", 15));
//        topContainter.add(new UI_Button_MenuButton("Main menu", 15));
//
//        UIContainer bottomContainer = new UIContainer(400, 128);
//        bottomContainer.setPadding(new ContainerPadding(10));
//        bottomContainer.setBackground(Color.GREEN);
//
//        UIContainer optionsPane = new UIContainer(150, 128);
//        optionsPane.setBackground(Color.ORANGE);
//        optionsPane.setBorder(new ContainerBorder(5));
//
//        UITextBox optionsTitle = new UITextBox("Options", 15);
//        optionsTitle.setAlignment(UIAlignment.NORTH);
//        optionsPane.add(optionsTitle);
//
//
//        UIContainer debugPane = new UIContainer(150, 128);
//        debugPane.setBackground(Color.BLUE);
//        debugPane.setBorder(new ContainerBorder(5));
//
//        UITextBox debugTitle = new UITextBox("Debug", 15);
//        debugTitle.setAlignment(UIAlignment.NORTH);
//        debugPane.add(debugTitle);
//
//        bottomContainer.add(optionsPane);
//        bottomContainer.add(debugPane);
//
//
//        this.add(title);
//        this.add(topContainter);
//        this.add(bottomContainer);
    }
}
