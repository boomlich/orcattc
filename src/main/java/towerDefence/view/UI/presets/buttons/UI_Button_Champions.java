package towerDefence.view.UI.presets.buttons;

import towerDefence.tower.Cost;
import towerDefence.view.GameRenderable;
import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.UI_ToolTip;

import java.awt.*;

public class UI_Button_Champions extends UIButton {

    public static final UI_Button_Champions ARCHER = new UI_Button_Champions(
            Cost.ARCHER.getBuyCost(),
            InteractCode.SPAWN_A,
            "graphics/buttons/archer/Portrait_Bow_Normal.png",
            "graphics/buttons/archer/Portrait_Bow_Hover.png",
            "graphics/buttons/archer/Portrait_Bow_Clicked.png",
            "graphics/buttons/archer/Portrait_Bow_Disabled.png",
            "Some men just want to watch the world burn. This is one of such men. Fires an burning inferno upon his enemies."
            );

    public static final UI_Button_Champions RIFLEMAN = new UI_Button_Champions(
            Cost.RIFLEMAN.getBuyCost(),
            InteractCode.SPAWN_B,
            "graphics/buttons/gun/Portrait_Gun_Normal.png",
            "graphics/buttons/gun/Portrait_Gun_Hover.png",
            "graphics/buttons/gun/Portrait_Gun_Clicked.png",
            "graphics/buttons/gun/Portrait_Gun_Disabled.png",
            "Equipped with his trusted blunderbuss that fires up to three penetrating bullets."
    );

    public static UI_Button_Champions CANNON = new UI_Button_Champions(
            Cost.CANNON.getBuyCost(),
            InteractCode.SPAWN_C,
            "graphics/buttons/gun/Portrait_Gun_Normal.png",
            "graphics/buttons/gun/Portrait_Gun_Hover.png",
            "graphics/buttons/gun/Portrait_Gun_Clicked.png",
            "graphics/buttons/gun/Portrait_Gun_Disabled.png",
            "Loaded with high-explosive cannon-balls that obliterate nearby enemies."
    );

    public static UI_Button_Champions WIZARD = new UI_Button_Champions(
            Cost.WIZARD.getBuyCost(),
            InteractCode.SPAWN_D,
            "graphics/buttons/archer/Portrait_Bow_Normal.png",
            "graphics/buttons/archer/Portrait_Bow_Hover.png",
            "graphics/buttons/archer/Portrait_Bow_Clicked.png",
            "graphics/buttons/archer/Portrait_Bow_Disabled.png",
            "Trained in the ancient art of frost magic. Attacks with a flurry of icicles that freezes enemies."
    );

    private final int cost;
    private final UITextBox priceText;
    private UI_ToolTip toolTip;
    private final String toolTipText;



    private UI_Button_Champions(int cost, InteractCode interactCode, String normal, String hover, String clicked, String disabled, String toolTiptext) {
        super(32, 32);

        this.cost = cost;
        this.toolTipText = toolTiptext;

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

        if (getButtonState() == UIButtonState.HOVER) {
            addToolTip();
        } else {
            removeToolTip();
        }
    }

    private void removeToolTip() {
        if (toolTip != null) {
            remove(toolTip);
            toolTip = null;
        }
    }

    private void addToolTip() {
        if (toolTip == null) {
            toolTip = new UI_ToolTip(toolTipText);
            this.add(toolTip);
            toolTip.offsetPosition(0, -70);
        }
    }
}
