package towerDefence.view.UI.presets;

import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.UI.components.ContainerPadding;
import towerDefence.view.UI.components.UIButton;
import towerDefence.view.UI.components.UIContainer;

import java.awt.*;

public class UI_Champ extends UIContainer {
    public UI_Champ(int width, int height) {
        super(width, height);

        setBackground(Color.BLUE);
        setPadding(new ContainerPadding(20));

        int buttonSize = 96;

        UIButton b1 = new UIButton("A", buttonSize, buttonSize);
        UIButton b2 = new UIButton("B", buttonSize, buttonSize);

        b1.setInteractCode(InteractCode.SPAWN_A);
        b2.setInteractCode(InteractCode.SPAWN_B);

        add(b1);
        add(b2);
    }



}
