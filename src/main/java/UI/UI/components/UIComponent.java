package UI.UI.components;

import java.awt.*;
import java.util.List;

public interface UIComponent {

    /**
     * @return width of component
     */
    int getWidth();

    /**
     * @return height of component
     */
    int getHeight();

    /**
     * Set the width of the component to a new value
     *
     * @param width new width
     */
    void setWidth(int width);

    /**
     * Set the height of the component to a new value
     *
     * @param height new height
     */
    void setHeight(int height);

    /**
     * @return x coordinate of the component
     */
    public int getX();

    /**
     * @return y coordinate of the component
     */
    public int getY();

    /**
     * @param x new x coordinate of the component
     */
    public void setX(int x);

    /**
     * @param y new y coordinate of the component
     */
    public void setY(int y);

    /**
     * @return the current active alignment mode of the component
     */
    public UIAlignment getAlignment();

    /**
     * Set a new alignment mode. Alignment mode controls where to
     * component is positioned inside other components such as containers
     * or buttons.
     *
     * @param alignment new alignment mode
     */
    void setAlignment(UIAlignment alignment);

    void paint(Graphics2D g2D);

    /**
     * Update the position of the container
     */
    void updatePosition();

    /**
     * @return list of all subcomponents inside the component
     */
    List<UIComponent> getComponents();

    /**
     * Update position of all components and subcomponents
     *
     * @param component the target component where every subcomponent
     * is to be updated
     */
    void updateAllPositions(UIComponent component);

    /**
     * Go through all components and subcomponents to tag them as inactive.
     * When InteractionManager is updated it will remove inactive components.
     * @param component
     */
    public void setAllSubComponentsInactive(UIComponent component);

    /**
     * Offset the components x and y coordinate from the regular alignment
     * position.
     *
     * @param offsetX offset in x coordinate
     * @param offsetY offset in y coordinate
     */
    public void offsetPosition(int offsetX, int offsetY);

}
