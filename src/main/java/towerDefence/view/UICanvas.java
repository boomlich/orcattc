package towerDefence.view;

import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.UI_HUD;

import java.awt.*;


public class UICanvas implements ICanvas {

    private GameRenderable gameModel;

    private final UIContainer UIContainer;

    public UICanvas(GameRenderable gameModel, int width, int height) {
        UIContainer = new UIContainer(width, height);
        UIContainer.add(new UI_HUD(width, height));
    }

    @Override
    public void paint(Graphics2D g2D) {
        UIContainer.paint(g2D);
    }
}
