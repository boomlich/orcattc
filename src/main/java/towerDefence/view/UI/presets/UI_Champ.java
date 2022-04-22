package towerDefence.view.UI.presets;

import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.buttons.UI_Button_Archer;
import towerDefence.view.UI.presets.buttons.UI_Button_Cannon;
import towerDefence.view.UI.presets.buttons.UI_Button_Gunner;
import towerDefence.view.UI.presets.buttons.UI_Button_Wizard;

import java.awt.*;

public class UI_Champ extends UIContainer {

    public UI_Champ(int width, int height) {
        super(width, height);

        this.setLayoutManager(UILayout.VERTICAL);

        UIButton[] champions = {
                new UI_Button_Archer(),
                new UI_Button_Gunner(),
                new UI_Button_Cannon(),
                new UI_Button_Wizard(),
        };

        UIContainer topContainer = new UIContainer(width, 10);
        topContainer.setAlignment(UIAlignment.SOUTH_WEST);
        topContainer.setBorder(new ContainerBorder(0, 0, 5, 10));

        UITextBox title = new UITextBox("Champions", 10, Color.WHITE);
        title.setFont(new Font("Calibri", Font.BOLD, 11));
        title.setAlignment(UIAlignment.SOUTH_WEST);
        topContainer.add(title);

        UIContainer bottomContainer = new UIContainer(width, 47);
        bottomContainer.setAlignment(UIAlignment.SOUTH_WEST);

        UIContainer frameLeft = new UIContainer(12, 47);
        frameLeft.setAlignment(UIAlignment.WEST);
        frameLeft.setBackgroundImage("graphics/UI/ChampionSelect/UI_Champions_Frame_Left.png");

        int bodyPadding = 2;
        int bodyLength = champions[0].getWidth() * champions.length + bodyPadding * (champions.length - 1);

        UIContainer frameBody = new UIContainer(bodyLength, 47);
        frameBody.setAlignment(UIAlignment.WEST);
        frameBody.setPadding(new ContainerPadding(bodyPadding));
        frameBody.setBackgroundImage("graphics/UI/ChampionSelect/UI_Champions_Frame_Body.png");

        UIContainer frameRight = new UIContainer(12, 47);
        frameRight.setAlignment(UIAlignment.WEST);
        frameRight.setBackgroundImage("graphics/UI/ChampionSelect/UI_Champions_Frame_Right.png");


        bottomContainer.add(frameLeft);
        bottomContainer.add(frameBody);
        bottomContainer.add(frameRight);

        for (UIButton champion: champions) {
            frameBody.add(champion);
        }

        this.add(bottomContainer);
        this.add(topContainer);
    }
}
