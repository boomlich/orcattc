package UI.UI.components;

import UI.Interaction.Interactable;
import UI.Interaction.InteractionManager;
import towerDefence.view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UIContainer extends UIComponentTemplate{

    /**
     * List of all components inside the container
     */
    private List<UIComponent> components = new ArrayList<>();
    private ContainerBorder border = new ContainerBorder(0);
    private ContainerPadding padding = new ContainerPadding(0);
    private Color background;
    private UILayoutManager layoutManager;
    private BufferedImage backgroundImg;

    public UIContainer(int width, int height) {
        super(width, height);
        setAlignment(UIAlignment.CENTER);
        setLayoutManager(UILayout.HORIZONTAL);
    }

    /**
     * Fill the container with a solid color
     * @param color selected color
     */
    public void setBackground(Color color) {
        background = color;
    }

    /**
     * Check if two components have equal alignment modes.
     *
     * @param compA first component
     * @param compB second component
     * @return true if equal
     */
    private boolean equalAlignment(UIComponent compA, UIComponent compB) {
        return compA.getAlignment() == compB.getAlignment();
    }

    /**
     * Set a new layout mode of the container. Layout determines
     * how equally aligned components are distributed and positioned.
     * If horizontal, the equally aligned components will be distributed
     * horizontally, whilst when vertical, the equally aligned
     * components will be distributed vertically.
     *
     * @param layout new layout
     */
    public void setLayoutManager(UILayout layout) {
        layoutManager = new UILayoutManager(layout);
    }

    /**
     * Fill the container will an image. The image will
     * be stretched to fit the dimensions of the container.
     *
     * @param path path of the image
     */
    public void setBackgroundImage(String path) {
        try {
            backgroundImg = ImageLoader.loadBufferedImage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fill the container will an image. The image will
     * be stretched to fit the dimensions of the container.
     *
     * @param image selected image as background
     */
    public void setBackgroundImage(BufferedImage image) {
        backgroundImg = image;
    }

    /**
     * Set the border of the container. The border will
     * determine the maximum and minimum position across
     * the edges of the container. If border is set to 5,
     * and the alignment is set to NORTH, the NORTH objects
     * will be offset by 5 in the y-coordinate.
     *
     * @param border desired border for the container
     */
    public void setBorder(ContainerBorder border) {
        this.border = border;
    }

    /**
     * Set the padding between equally aligned components
     * added inside the container. Does not affect components
     * with different alignment.
     *
     * @param padding desired padding for the container
     */
    public void setPadding(ContainerPadding padding) {
        this.padding = padding;
    }

    /**
     * Position the component inside the container according
     * to the set alignment, padding, border and other
     * equally aligned components already added to the container.
     * Updates position of every subcomponent, to distribute the new
     * component correctly. This includes subcomponents of subcomponents.
     *
     * @param component component to be added
     */
    public void add(UIComponent component) {
        positionComponent(component);
        components.add(component);
        component.updatePosition();
        updateAllPositions(component);
    }

    /**
     * Remove the target component. Also removes all subcomponent inside
     * the target component. This includes interactable objects.
     *
     * @param component component to be removed
     */
    public void remove(UIComponent component) {
        setAllSubComponentsInactive(component);
        components.remove(component);
        InteractionManager.removeInactive();
    }

    @Override
    public void setAllSubComponentsInactive(UIComponent component) {
        if (component != null) {
            if (component instanceof Interactable) {
                ((Interactable) component).setInactive();
            }
            if (component.getComponents() != null) {
                for (UIComponent subcomponent: component.getComponents()) {
                    setAllSubComponentsInactive(subcomponent);
                }
            }
        }
    }

    @Override
    public void updateAllPositions(UIComponent component) {
        if (component.getComponents() != null) {
            for (UIComponent subComponent: component.getComponents()) {
                subComponent.updatePosition();
                updateAllPositions(subComponent);
            }
        }
    }

    @Override
    public void updatePosition() {
        List<UIComponent> copyOfComponents = new ArrayList<>(List.copyOf(components));
        components = new ArrayList<>();

        for (UIComponent component: copyOfComponents) {
            positionComponent(component);
            components.add(component);
        }
    }

    @Override
    public List<UIComponent> getComponents(){
        return components;
    }

    /**
     * Set the position of the component inside the container according to
     * its alignement, the containers padding and border, and other
     * components previously added. If center of layout axis, all components
     * are redistributed evenly on the axis te account for the new component.
     *
     * @param component component to be positioned
     */
    private void positionComponent(UIComponent component) {
        List<UIComponent> equalAlignmentComponents = findEqualAlignment(component);

        Point position = layoutManager.assignPositionsToComponents(getX(), getY(), getWidth(), getHeight(),
                border, padding,component, equalAlignmentComponents);

        component.setX(position.x);
        component.setY(position.y);
    }

    /**
     * @param component component to compare all other components with.
     * @return list of equally aligned components inside the container.
     */
    private List<UIComponent> findEqualAlignment(UIComponent component) {
        List<UIComponent> equalComponents = new ArrayList<>();

        for (UIComponent potentialComponent: components) {
            if (equalAlignment(component, potentialComponent)) {
                equalComponents.add(potentialComponent);
            }
        }
        return equalComponents;
    }


    @Override
    public void paint(Graphics2D g2D){
        if (background != null) {
            g2D.setColor(background);
            g2D.fill(new Rectangle(getX(), getY(), getWidth(), getHeight()));
        }

        if (backgroundImg != null) {
            g2D.drawImage(backgroundImg, getX(), getY(), getWidth(), getHeight(), null);
        }

        for (UIComponent component: components) {
            component.paint(g2D);
        }
    }
}
