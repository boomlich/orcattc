package towerDefence.view.UI.presets.buttons;

import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.UIAlignment;
import towerDefence.view.UI.components.UIButton;
import towerDefence.view.UI.components.UITextBox;

import java.awt.*;

public class UI_Button_Pause_Resume extends UIButton {
    public UI_Button_Pause_Resume() {
        super(125, 20);

        setInteractCode(InteractCode.PAUSE);

        UITextBox text = new UITextBox("RESUME GAME", 15, Color.WHITE);
        text.setFont(new Font("Calibri", Font.BOLD, 15));
        this.add(text);
    }
}
