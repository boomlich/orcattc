package UI.UI.presets.buttons;

import UI.Interaction.InteractCode;
import UI.UI.components.UIButton;

public class UI_Button_HUD extends UIButton {

    public UI_Button_HUD() {
        super(40, 40);
    }

    public void setPlay() {
        setInteractCode(InteractCode.PLAY);
        setButtonNormal("graphics/UI/PlayRound/UI_PlayRound_Normal.png");
        setButtonHover("graphics/UI/PlayRound/UI_PlayRound_Hover.png");
        setButtonClicked("graphics/UI/PlayRound/UI_PlayRound_Normal.png");
    }

    public void setFastForwardDisabled() {
        setInteractCode(InteractCode.FAST_FORWARD);
        setButtonNormal("graphics/UI/PlayRound/UI_FastForward_Disabled_Normal.png");
        setButtonHover("graphics/UI/PlayRound/UI_FastForward_Disabled_Hover.png");
        setButtonClicked("graphics/UI/PlayRound/UI_FastForward_Disabled_Normal.png");
    }

    public void setFastForwardEnabled() {
        setInteractCode(InteractCode.FAST_FORWARD);
        setButtonNormal("graphics/UI/PlayRound/UI_FastForward_Enabled_Normal.png");
        setButtonHover("graphics/UI/PlayRound/UI_FastForward_Enabled_Hover.png");
        setButtonClicked("graphics/UI/PlayRound/UI_FastForward_Enabled_Normal.png");
    }

    public void toggleFastForward(boolean isFastForwarding) {
        if (isFastForwarding) {
            setFastForwardEnabled();
        } else {
            setFastForwardDisabled();
        }
    }
}
