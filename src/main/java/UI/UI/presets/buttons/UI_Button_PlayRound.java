package UI.UI.presets.buttons;

import UI.Interaction.InteractCode;
import UI.UI.components.UIButton;

public class UI_Button_PlayRound extends UIButton {

    public UI_Button_PlayRound() {
        super(40, 40);

        setInteractCode(InteractCode.PLAY);

        setButtonNormal("graphics/UI/PlayRound/UI_PlayRound_Normal.png");
        setButtonHover("graphics/UI/PlayRound/UI_PlayRound_Hover.png");
    }
}
