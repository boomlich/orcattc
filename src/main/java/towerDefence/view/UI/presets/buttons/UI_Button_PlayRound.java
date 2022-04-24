package towerDefence.view.UI.presets.buttons;

import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.ContainerBorder;
import towerDefence.view.UI.components.UIAlignment;
import towerDefence.view.UI.components.UIButton;
import towerDefence.view.UI.components.UITextBox;

import java.awt.*;

public class UI_Button_PlayRound extends UIButton {

    public UI_Button_PlayRound() {
        super(40, 40);

        setInteractCode(InteractCode.PLAY);

        setButtonNormal("graphics/UI/PlayRound/UI_PlayRound_Normal.png");
        setButtonHover("graphics/UI/PlayRound/UI_PlayRound_Hover.png");
    }
}
