package towerDefence.view.UI.presets.buttons;

import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.ContainerBorder;
import towerDefence.view.UI.components.UIAlignment;
import towerDefence.view.UI.components.UIButton;
import towerDefence.view.UI.components.UITextBox;

import java.awt.*;

public class UI_Button_Wizard extends UIButton {
    public UI_Button_Wizard() {
        super(32, 32);

        setInteractCode(InteractCode.SPAWN_D);

        setButtonNormal("graphics/buttons/archer/Portrait_Bow_Normal.png");
        setButtonHover("graphics/buttons/archer/Portrait_Bow_Hover.png");
        setButtonClicked("graphics/buttons/archer/Portrait_Bow_Clicked.png");
        setButtonDisabled("graphics/buttons/archer/Portrait_Bow_Disabled.png");

        UITextBox priceText = new UITextBox("$1000", 8, Color.WHITE);
        priceText.setFont(new Font("Calibri", Font.BOLD, 10));
        priceText.setAlignment(UIAlignment.SOUTH);

        this.setBorder(new ContainerBorder(0, 0, 4, 0));

        this.add(priceText);
    }
}
