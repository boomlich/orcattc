package towerDefence.view;

import towerDefence.view.sprite.Sprite;

public interface IRenderableObject {

    /**
     * @return the objects current z-depth value. Used to
     * render objects in correct order.
     */
    public int getZDepth();

    /**
     * @return
     */
    public Sprite getSprite();

    public void update(double deltaSteps);

    /**
     * Objects that is set to be removed is tagged dead
     *
     * @return true if object is dead
     */
    boolean isDead();
}
