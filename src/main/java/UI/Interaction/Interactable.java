package UI.Interaction;

public interface Interactable {

    int getX();

    int getY();

    int getWidth();

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
     *
     */
    void toggleClick();

    /**
     *
     * @return
     */
    InteractCode getInteractCode();

    /**
     *
     */
    void disableInteraction();

    /**
     *
     */
    void enableInteraction();

    /**
     *
     */
    void setInactive();
}
