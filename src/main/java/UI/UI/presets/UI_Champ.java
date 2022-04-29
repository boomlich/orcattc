package UI.UI.presets;

import UI.UI.components.*;
import UI.UI.presets.buttons.UI_Button_Champions;
import towerDefence.tower.Cost;
import towerDefence.view.GameRenderable;
import UI.Interaction.InteractCode;

import java.awt.*;

public class UI_Champ extends UIContainer {

    private final UI_Button_Champions[] champions;

    public UI_Champ(int width, int height) {
        super(width, height);

        this.setLayoutManager(UILayout.VERTICAL);

        UI_Button_Champions archer = new UI_Button_Champions(
                Cost.ARCHER.getBuyCost(),
                InteractCode.SPAWN_A,
                "graphics/buttons/archer/Portrait_Bow_Normal.png",
                "graphics/buttons/archer/Portrait_Bow_Hover.png",
                "graphics/buttons/archer/Portrait_Bow_Clicked.png",
                "graphics/buttons/archer/Portrait_Bow_Disabled.png",
                "Fires burning arrows that set enemies on fire"
        );

        UI_Button_Champions rifleMan = new UI_Button_Champions(
                Cost.RIFLEMAN.getBuyCost(),
                InteractCode.SPAWN_B,
                "graphics/buttons/gun/Portrait_Gun_Normal.png",
                "graphics/buttons/gun/Portrait_Gun_Hover.png",
                "graphics/buttons/gun/Portrait_Gun_Clicked.png",
                "graphics/buttons/gun/Portrait_Gun_Disabled.png",
                "Fires blunderbuss that fires up to three penetrating bullets"
        );

        UI_Button_Champions cannon = new UI_Button_Champions(
                Cost.CANNON.getBuyCost(),
                InteractCode.SPAWN_C,
                "graphics/buttons/cannon/Portrait_Cannon_Normal.png",
                "graphics/buttons/cannon/Portrait_Cannon_Hover.png",
                "graphics/buttons/cannon/Portrait_Cannon_Clicked.png",
                "graphics/buttons/cannon/Portrait_Cannon_Disabled.png",
                "Fires high-explosive cannon-balls"
        );

        UI_Button_Champions wizard = new UI_Button_Champions(
                Cost.WIZARD.getBuyCost(),
                InteractCode.SPAWN_D,
                "graphics/buttons/wizard/Portrait_Wizard_Normal.png",
                "graphics/buttons/wizard/Portrait_Wizard_Hover.png",
                "graphics/buttons/wizard/Portrait_Wizard_Clicked.png",
                "graphics/buttons/wizard/Portrait_Wizard_Disabled.png",
                "Attacks with a flurry of icicles that freezes enemies"
        );

        champions = new UI_Button_Champions[]{
                archer,
                rifleMan,
                cannon,
                wizard,
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

        for (UIButton champion: champions) {
            frameBody.add(champion);
        }

        bottomContainer.add(frameLeft);
        bottomContainer.add(frameBody);
        bottomContainer.add(frameRight);

        this.add(bottomContainer);
        this.add(topContainer);
    }

    public void update(GameRenderable gameModel) {
        for (UI_Button_Champions button: champions) {
            button.update(gameModel);
        }
    }
}
