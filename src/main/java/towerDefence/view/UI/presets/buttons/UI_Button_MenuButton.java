package towerDefence.view.UI.presets.buttons;

import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.ContainerBorder;
import towerDefence.view.UI.components.ContainerPadding;
import towerDefence.view.UI.components.UIButton;
import towerDefence.view.UI.components.UITextBox;

import java.awt.*;

public class UI_Button_MenuButton extends UIButton {
    public UI_Button_MenuButton(String buttonText, int size) {
        super(125, 20);

        setInteractCode(InteractCode.PAUSE);

        UITextBox text = new UITextBox(buttonText, 15, Color.WHITE);
        text.setFont(new Font("Calibri", Font.BOLD, size));
        this.add(text);
    }
}
