package towerDefence.view.Interaction;

public interface Interactable {

    public int getX();

    public int getY();

    public int getWidth();

    public int getHeight();

    public String getTooltip();

    public void toggleHover();

    public void toggleClick();

    public InteractCode getInteractCode();

    void disableInteraction();

    void enableInteraction();

    public void setInactive();
}
