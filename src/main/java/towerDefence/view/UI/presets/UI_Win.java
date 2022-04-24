package towerDefence.view.UI.presets;

import towerDefence.view.UI.components.UIContainer;
import towerDefence.view.UI.components.UITextBox;

import java.awt.*;

public class UI_Win extends UIContainer {

    public UI_Win(int width, int height) {
        super(width, height);


        UIContainer container = new UIContainer(125, 300);
        container.setBackground(Color.orange);

        UITextBox title = new UITextBox("Winner!", 25);
        container.add(title);

        this.add(container);
    }


}
