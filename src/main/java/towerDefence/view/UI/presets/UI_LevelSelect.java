package towerDefence.view.UI.presets;


import towerDefence.level.levels.Level;
import towerDefence.view.GameRenderable;
import towerDefence.view.UI.components.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UI_LevelSelect extends UIContainer {

    GameRenderable gameModel;

    public UI_LevelSelect(int width, int height) {
        super(width, height);




        int frameEndSize = 12;
        int frameHeight = 300;
        int levelHeight = 178;

        UIContainer frameLeft = new UIContainer(frameEndSize, frameHeight);
        frameLeft.setBackgroundImage("graphics/UI/LevelSelect/UI_LevelSelect_Frame_Left.png");

        UIContainer frameRight = new UIContainer(frameEndSize, frameHeight);
        frameRight.setBackgroundImage("graphics/UI/LevelSelect/UI_LevelSelect_Frame_Right.png");

        UIContainer frameMain = new UIContainer(500 - 2 * frameEndSize, frameHeight);
        frameMain.setBackgroundImage("graphics/UI/LevelSelect/UI_LevelSelect_Frame_Main.png");
        frameMain.setLayoutManager(UILayout.VERTICAL);
        frameMain.setPadding(new ContainerPadding(20));


        UITextBox title = new UITextBox("Select level", 25);

        UIContainer mainContent = new UIContainer(500, levelHeight);
//        mainContent.setBackground(Color.RED);
        mainContent.setPadding(new ContainerPadding(20));

        List<Level> levelList = new ArrayList<>();
        levelList.add(Level.A);
        levelList.add(Level.B);

        int i = 0;
        for (Level level: levelList) {
            UIContainer levelContainer = new UIContainer(200, 178);
            levelContainer.setLayoutManager(UILayout.VERTICAL);
            levelContainer.setBackgroundImage("graphics/UI/LevelSelect/UI_LevelSelect_LevelFrame.png");
            levelContainer.setPadding(new ContainerPadding(5));

            UIButton levelThumbnail = new UIButton(200, 112);
            levelThumbnail.setButtonNormal(level.getThumbnailNormalPath());
            levelThumbnail.setButtonHover(level.getThumbnailHoverPath());
            levelThumbnail.setInteractCode(level.getInteractCode());

            UITextBox levelTitle = new UITextBox(level.getTitle(), 12);
            UITextBox levelDescription = new UITextBox(level.getLevelDescription(), 8);

            UIContainer details = new UIContainer(200, 30);
            details.setPadding(new ContainerPadding(5));

            UIContainer healthIcon = new UIContainer(15, 12);
            healthIcon.setBackgroundImage("graphics/UI/ResourceBar/UI_ResourceBar_HealthIcon.png");
            UITextBox health = new UITextBox(String.valueOf(level.getStartHealth()), 12);

            UIContainer padding = new UIContainer(10, 5);

            UIContainer moneyIcon = new UIContainer(12, 12);
            moneyIcon.setBackgroundImage("graphics/UI/ResourceBar/UI_ResourceBar_MoneyIcon.png");
            UITextBox money = new UITextBox(String.valueOf(level.getStartMoney()), 12);

            UIContainer padding2 = new UIContainer(10, 5);

            UITextBox waves = new UITextBox("Waves:", 12);
            UITextBox waveCounter = new UITextBox(String.valueOf(level.getMaxWaves()), 10);

            details.add(healthIcon);
            details.add(health);
            details.add(padding);
            details.add(moneyIcon);
            details.add(money);
            details.add(padding2);
            details.add(waves);
            details.add(waveCounter);

            levelContainer.add(levelThumbnail);
            levelContainer.add(levelTitle);
            levelContainer.add(levelDescription);
            levelContainer.add(details);
            mainContent.add(levelContainer);
            i ++;
        }

        frameMain.add(title);
        frameMain.add(mainContent);

        this.add(frameLeft);
        this.add(frameMain);
        this.add(frameRight);
    }
}
