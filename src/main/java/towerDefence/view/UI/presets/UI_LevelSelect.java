package towerDefence.view.UI.presets;


import towerDefence.level.levels.Level;
import towerDefence.view.GameRenderable;
import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.buttons.UI_Button_MenuButton;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UI_LevelSelect extends UIContainer {

    GameRenderable gameModel;

    public UI_LevelSelect(int width, int height) {
        super(width, height);
        this.setLayoutManager(UILayout.VERTICAL);


        UITextBox title = new UITextBox("Select level", 25);

        UIContainer mainContainer = new UIContainer(500, 300);
        mainContainer.setBackground(Color.RED);
        mainContainer.setPadding(new ContainerPadding(20));

        List<Level> levelList = new ArrayList<>();
        levelList.add(Level.A);
        levelList.add(Level.B);

        int i = 0;
        for (Level level: levelList) {
            UIContainer levelContainer = new UIContainer(200, 200);
            levelContainer.setLayoutManager(UILayout.VERTICAL);
            levelContainer.setPadding(new ContainerPadding(5));

            UIButton levelThumbnail = new UIButton(200, 150);
            if (i == 0) {
                levelThumbnail.setInteractCode(InteractCode.LEVEL_A);
            } else if (i == 1) {
                levelThumbnail.setInteractCode(InteractCode.LEVEL_B);
            }

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

            details.add(healthIcon);
            details.add(health);
            details.add(padding);
            details.add(moneyIcon);
            details.add(money);


            levelContainer.add(levelThumbnail);
            levelContainer.add(levelTitle);
            levelContainer.add(levelDescription);
            levelContainer.add(details);
            mainContainer.add(levelContainer);
            i ++;
        }
        this.add(title);
        this.add(mainContainer);
    }
}
