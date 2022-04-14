package towerDefence.view.UI.presets;

import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.buttons.UI_Button_PlayRound;

import java.awt.*;

public class UI_HUD extends UIContainer {

    public UI_HUD(int width, int height) {
        super(width, height);

        setBorder(new ContainerBorder(20, 50, 20, 50));


        UI_Champ ui_champ = new UI_Champ(200, 200);
        ui_champ.setAlignment(UIAlignment.SOUTH_WEST);

        this.add(ui_champ);

        UIButton playRound = new UI_Button_PlayRound();
        playRound.setAlignment(UIAlignment.SOUTH);
        this.add(playRound);

    }
}

