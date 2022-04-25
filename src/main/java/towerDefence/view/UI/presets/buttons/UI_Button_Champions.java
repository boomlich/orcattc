package towerDefence.view.UI.presets.buttons;

import towerDefence.tower.Cost;
import towerDefence.view.GameRenderable;
import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.ContainerBorder;
import towerDefence.view.UI.components.UIAlignment;
import towerDefence.view.UI.components.UIButton;
import towerDefence.view.UI.components.UITextBox;

import java.awt.*;

public class UI_Button_Champions extends UIButton {

    public static final UI_Button_Champions ARCHER = new UI_Button_Champions(
            Cost.ARCHER.getBuyCost(),
            InteractCode.SPAWN_A,
            "graphics/buttons/archer/Portrait_Bow_Normal.png",
            "graphics/buttons/archer/Portrait_Bow_Hover.png",
            "graphics/buttons/archer/Portrait_Bow_Clicked.png",
            "graphics/buttons/archer/Portrait_Bow_Disabled.png"
            );

    public static final UI_Button_Champions RIFLEMAN = new UI_Button_Champions(
            Cost.RIFLEMAN.getBuyCost(),
            InteractCode.SPAWN_B,
            "graphics/buttons/gun/Portrait_Gun_Normal.png",
            "graphics/buttons/gun/Portrait_Gun_Hover.png",
            "graphics/buttons/gun/Portrait_Gun_Clicked.png",
            "graphics/buttons/gun/Portrait_Gun_Disabled.png"
    );

    public static UI_Button_Champions CANNON = new UI_Button_Champions(
            Cost.CANNON.getBuyCost(),
            InteractCode.SPAWN_C,
            "graphics/buttons/gun/Portrait_Gun_Normal.png",
            "graphics/buttons/gun/Portrait_Gun_Hover.png",
            "graphics/buttons/gun/Portrait_Gun_Clicked.png",
            "graphics/buttons/gun/Portrait_Gun_Disabled.png"
    );

    public static UI_Button_Champions WIZARD = new UI_Button_Champions(
            Cost.WIZARD.getBuyCost(),
            InteractCode.SPAWN_D,
            "graphics/buttons/archer/Portrait_Bow_Normal.png",
            "graphics/buttons/archer/Portrait_Bow_Hover.png",
            "graphics/buttons/archer/Portrait_Bow_Clicked.png",
            "graphics/buttons/archer/Portrait_Bow_Disabled.png"
    );

    private final int cost;
    private final UITextBox priceText;



    private UI_Button_Champions(int cost, InteractCode interactCode, String normal, String hover, String clicked, String disabled) {
        super(32, 32);

        this.cost = cost;

        setButtonNormal(normal);
        setButtonHover(hover);
        setButtonClicked(clicked);
        setButtonDisabled(disabled);

        setInteractCode(interactCode);

        priceText = new UITextBox("$" + cost, 8, Color.WHITE);
        priceText.setFont(new Font("Calibri", Font.BOLD, 10));
        priceText.setAlignment(UIAlignment.SOUTH);

        this.setBorder(new ContainerBorder(0, 0, 4, 0));
        this.add(priceText);
    }

    public void update(GameRenderable gameRenderable) {
        if (gameRenderable.hasSufficiantFunds(cost)) {
            priceText.setColor(Color.WHITE);
            this.enableInteraction();
        } else {
            priceText.setColor(Color.RED);
            this.disableInteraction();
        }
    }
}
