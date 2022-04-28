package UI.UI.presets.buttons;

import UI.UI.components.ContainerBorder;
import UI.UI.components.UIAlignment;
import UI.UI.components.UIButton;
import UI.UI.components.UITextBox;
import UI.UI.presets.UI_ToolTip;
import towerDefence.view.GameRenderable;
import UI.Interaction.InteractCode;

import java.awt.*;
import java.awt.geom.Point2D;

public class UI_Button_Champions extends UIButton {

    private final int cost;
    private final UITextBox priceText;
    private UI_ToolTip toolTip;
    private final String toolTipText;

    public UI_Button_Champions(int cost, InteractCode interactCode, String normal, String hover, String clicked, String disabled, String toolTiptext) {
        super(32, 32);

        this.cost = cost;
        this.toolTipText = toolTiptext;

        setToolTip(toolTipText, new Point2D.Double(0, -70));

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
