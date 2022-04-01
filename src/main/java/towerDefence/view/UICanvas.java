package towerDefence.view;

import java.awt.*;

public class UICanvas implements ICanvas{

    GameRenderable gameModel;

    public UICanvas(GameRenderable gameModel) {
        this.gameModel = gameModel;
    }


    @Override
    public void paint(Graphics2D g2D) {

    }
}
