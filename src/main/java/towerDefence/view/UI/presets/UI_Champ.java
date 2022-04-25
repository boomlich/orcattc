package towerDefence.view.UI.presets;

import towerDefence.view.GameRenderable;
import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.buttons.*;

import java.awt.*;

public class UI_Champ extends UIContainer {

    private final UI_Button_Champions[] champions;

    public UI_Champ(int width, int height) {
        super(width, height);

        this.setLayoutManager(UILayout.VERTICAL);

        champions = new UI_Button_Champions[]{
                UI_Button_Champions.ARCHER,
                UI_Button_Champions.RIFLEMAN,
                UI_Button_Champions.CANNON,
                UI_Button_Champions.WIZARD,
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

    public void update(GameRenderable gameModel) {
        for (UI_Button_Champions button: champions) {
            button.update(gameModel);
        }
    }
}
