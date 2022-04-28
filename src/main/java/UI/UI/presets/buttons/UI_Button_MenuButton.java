package UI.UI.presets.buttons;

import UI.Interaction.InteractCode;
import UI.UI.components.UIButton;
import UI.UI.components.UITextBox;

import java.awt.*;

public class UI_Button_MenuButton extends UIButton {
    public UI_Button_MenuButton(String buttonText, int size, InteractCode interactCode) {
        super(129, 24);

        setInteractCode(interactCode);

        setButtonNormal("graphics/buttons/Menu/UI_Button_Menu_Big_Normal.png");
        setButtonHover("graphics/buttons/Menu/UI_Button_Menu_Big_Hover.png");
        setButtonClicked("graphics/buttons/Menu/UI_Button_Menu_Big_Normal.png");

        UITextBox text = new UITextBox(buttonText, 15, Color.WHITE);
        text.setFont(new Font("Calibri", Font.BOLD, size));
        this.add(text);
    }
}
