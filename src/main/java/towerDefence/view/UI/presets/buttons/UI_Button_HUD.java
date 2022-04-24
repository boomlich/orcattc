package towerDefence.view.UI.presets.buttons;

import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.UIButton;

public class UI_Button_HUD extends UIButton {

    public static UI_Button_HUD HUD_Play = new UI_Button_HUD(
            "graphics/UI/PlayRound/UI_PlayRound_Normal.png",
            "graphics/UI/PlayRound/UI_PlayRound_Hover.png",
            InteractCode.PLAY
    );

    public static UI_Button_HUD HUD_FastForward = new UI_Button_HUD(
            "graphics/buttons/archer/Portrait_Bow_Normal.png",
            "graphics/buttons/archer/Portrait_Bow_Normal.png",
            InteractCode.FAST_FORWARD
    );

    private UI_Button_HUD(String normal, String hover, InteractCode interactCode) {
        super(40, 40);

        setInteractCode(interactCode);

        setButtonNormal(normal);
        setButtonHover(hover);
    }
}
