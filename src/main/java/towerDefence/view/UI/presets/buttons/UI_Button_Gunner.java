package towerDefence.view.UI.presets.buttons;

import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.ContainerBorder;
import towerDefence.view.UI.components.UIAlignment;
import towerDefence.view.UI.components.UIButton;
import towerDefence.view.UI.components.UITextBox;

import java.awt.*;

public class UI_Button_Gunner extends UIButton {
    public UI_Button_Gunner() {
        super(32, 32);

        setInteractCode(InteractCode.SPAWN_B);

        setButtonNormal("graphics/buttons/gun/Portrait_Gun_Normal.png");
        setButtonHover("graphics/buttons/gun/Portrait_Gun_Hover.png");
        setButtonClicked("graphics/buttons/gun/Portrait_Gun_Clicked.png");
        setButtonDisabled("graphics/buttons/gun/Portrait_Gun_Disabled.png");

        UITextBox priceText = new UITextBox("$100", 8, Color.WHITE);
        priceText.setFont(new Font("Calibri", Font.BOLD, 10));
        priceText.setAlignment(UIAlignment.SOUTH);

        this.setBorder(new ContainerBorder(0, 0, 4, 0));

        this.add(priceText);
    }





}
