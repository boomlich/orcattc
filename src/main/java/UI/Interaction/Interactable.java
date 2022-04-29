package UI.Interaction;

public interface Interactable {

    /**
     * @return x-coordinate of the interactable box. Top left corner
     * of the interactable.
     */
    int getX();

    /**
     * @return y-coordinate of the interactable box. Top left corner
     * of the interactiable.
     */
    int getY();

    /**
     * @return width of the interactable object
     */
    int getWidth();

    /**
     * @return height of the interactable object
     */
    int getHeight();

    /**
     * Set to hover state.
     */
    void setHover();

    /**
     * set to normal state
     */
    void setNormal();

    /**
     * Toggle on and off of clicked state
     */
    void toggleClick();

    /**
     * @return interaction code of the interactable. Determines the
     * action that will take place after its pressed.
     */
    InteractCode getInteractCode();

    /**
     * Disables the interactable. No interaction takes place when
     * disabled.
     */
    void disableInteraction();

    /**
     * Enabled the interactable. Set to normal state.
     */
    void enableInteraction();

    /**
     * Set to inactive. Inactive interactables will be removed next update
     * by the InteractionManager
     */
    void setInactive();
}
