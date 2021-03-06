package UI.UI.presets;

import UI.UI.components.ContainerPadding;
import towerDefence.view.GameRenderable;
import UI.UI.components.UIContainer;
import UI.UI.components.UITextBox;

public class UI_ResourceBar extends UIContainer {

    UITextBox health;
    UITextBox money;

    public UI_ResourceBar(int width, int height) {
        super(width, height);

        int cornerWidth = 5;

        UIContainer leftCorner = new UIContainer(cornerWidth, height);
        leftCorner.setBackgroundImage("graphics/UI/ResourceBar/UI_ResourceBar_Frame_Left.png");

        UIContainer rightCorner = new UIContainer(cornerWidth, height);
        rightCorner.setBackgroundImage("graphics/UI/ResourceBar/UI_ResourceBar_Frame_Right.png");

        UIContainer mainFrame = new UIContainer(width - cornerWidth, height);
        mainFrame.setBackgroundImage("graphics/UI/ResourceBar/UI_ResourceBar_Frame_Main.png");


        UIContainer healthIcon = new UIContainer(15, 12);
        healthIcon.setBackgroundImage("graphics/UI/ResourceBar/UI_ResourceBar_HealthIcon.png");
        health = new UITextBox("100");

        UIContainer padding = new UIContainer(15, 10);

        UIContainer moneyIcon = new UIContainer(12, 12);
        moneyIcon.setBackgroundImage("graphics/UI/ResourceBar/UI_ResourceBar_MoneyIcon.png");

        money = new UITextBox("1000");

        this.add(leftCorner);
        this.add(mainFrame);
        this.add(rightCorner);

        mainFrame.setPadding(new ContainerPadding(5));
        mainFrame.add(healthIcon);
        mainFrame.add(health);
        mainFrame.add(padding);
        mainFrame.add(moneyIcon);
        mainFrame.add(money);
    }

    public void update(GameRenderable gameRenderable) {
        health.setText(String.valueOf(gameRenderable.getHealth()));
        money.setText(String.valueOf(gameRenderable.getMoney()));
    }
}
