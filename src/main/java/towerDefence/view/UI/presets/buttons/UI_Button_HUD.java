package towerDefence.view.UI.presets.buttons;

import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.UIButton;

public class UI_Button_HUD extends UIButton {

    public UI_Button_HUD() {
        super(40, 40);
    }

    public void setPlay() {
        setInteractCode(InteractCode.PLAY);
        setButtonNormal("graphics/UI/PlayRound/UI_PlayRound_Normal.png");
        setButtonHover("graphics/UI/PlayRound/UI_PlayRound_Hover.png");
    }

    public void setFastForwardDisabled() {
        setInteractCode(InteractCode.FAST_FORWARD);
        setButtonNormal("graphics/buttons/archer/Portrait_Bow_Normal.png");
        setButtonHover("graphics/buttons/archer/Portrait_Bow_Hover.png");
    }

    public void setFastForwardEnabled() {
        setInteractCode(InteractCode.FAST_FORWARD);
        setButtonNormal("graphics/buttons/gun/Portrait_Gun_Normal.png");
        setButtonHover("graphics/buttons/gun/Portrait_Gun_Hover.png");
    }

    public void toggleFastForward(boolean isFastForwarding) {
        if (isFastForwarding) {
            setFastForwardEnabled();
        } else {
            setFastForwardDisabled();
        }
    }
}
