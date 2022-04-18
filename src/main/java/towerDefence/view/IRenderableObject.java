package towerDefence.view;

import towerDefence.view.sprite.Sprite;

public interface IRenderableObject {

    public int getZDepth();

    public Sprite getSprite();

    public void update(double deltaSteps);
}
