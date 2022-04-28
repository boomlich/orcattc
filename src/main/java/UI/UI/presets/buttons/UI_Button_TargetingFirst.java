package UI.UI.presets.buttons;

import UI.Interaction.InteractCode;
import UI.UI.components.ContainerBorder;
import UI.UI.components.UIAlignment;
import UI.UI.components.UIButton;
import UI.UI.components.UITextBox;

import java.awt.*;

public class UI_Button_TargetingFirst extends UIButton {

    public UI_Button_TargetingFirst() {
        super(32, 32);

        setInteractCode(InteractCode.TARGET_FIRST);

        setButtonNormal("graphics/buttons/archer/Portrait_Bow_Normal.png");
        setButtonHover("graphics/buttons/archer/Portrait_Bow_Hover.png");
        setButtonClicked("graphics/buttons/archer/Portrait_Bow_Clicked.png");
        setButtonDisabled("graphics/buttons/archer/Portrait_Bow_Disabled.png");

        UITextBox priceText = new UITextBox("$100", 8, Color.WHITE);
        priceText.setFont(new Font("Calibri", Font.BOLD, 10));
        priceText.setAlignment(UIAlignment.SOUTH);

        this.setBorder(new ContainerBorder(0, 0, 4, 0));

        this.add(priceText);
    }
}
