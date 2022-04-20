package towerDefence.view.UI.presets.buttons;

import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.UIButton;

public class UI_Button_Options extends UIButton {

    public UI_Button_Options() {
        super(32, 32);

        setInteractCode(InteractCode.PAUSE);

        setButtonNormal("graphics/UI/PlayRound/UI_PlayRound_Normal.png");
        setButtonHover("graphics/UI/PlayRound/UI_PlayRound_Hover.png");
    }
}
