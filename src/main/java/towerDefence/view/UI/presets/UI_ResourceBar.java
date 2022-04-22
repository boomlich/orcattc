package towerDefence.view.UI.presets;

import towerDefence.view.UI.components.UIAlignment;
import towerDefence.view.UI.components.UIContainer;
import towerDefence.view.UI.components.UITextBox;

import java.awt.*;

public class UI_ResourceBar extends UIContainer {

    public UI_ResourceBar(int width, int height) {
        super(width, height);

        UIContainer healthIcon = new UIContainer(16, 16);
        healthIcon.setBackground(Color.RED);
        healthIcon.setAlignment(UIAlignment.CENTER);

        UITextBox health = new UITextBox("100");
        health.setAlignment(UIAlignment.CENTER);

        UIContainer padding = new UIContainer(10, 10);
        padding.setAlignment(UIAlignment.CENTER);

        UIContainer moneyIcon = new UIContainer(16, 16);
        moneyIcon.setBackground(Color.BLUE);
        moneyIcon.setAlignment(UIAlignment.CENTER);

        UITextBox money = new UITextBox("1000");
        money.setAlignment(UIAlignment.CENTER);

        this.add(healthIcon);
        this.add(health);
        this.add(padding);
        this.add(moneyIcon);
        this.add(money);
    }
}