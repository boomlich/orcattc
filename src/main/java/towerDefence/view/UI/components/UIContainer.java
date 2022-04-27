package towerDefence.view.UI.components;

import towerDefence.view.ImageLoader;
import towerDefence.view.Interaction.Interactable;
import towerDefence.view.Interaction.InteractionManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UIContainer extends UIComponentTemplate{

    private List<UIComponent> components = new ArrayList<>();

    private ContainerBorder border = new ContainerBorder(0);
    private ContainerPadding padding = new ContainerPadding(0);
    private Color background;

    private boolean inactive;

    private UILayoutManager layoutManager;

    private BufferedImage backgroundImg;

    public UIContainer(int width, int height) {
        super(width, height);
        setAlignment(UIAlignment.CENTER);
        setLayoutManager(UILayout.HORIZONTAL);
    }

    public void setBackground(Color color) {
        background = color;
    }

    private boolean equalAlignment(UIComponent compA, UIComponent compB) {
        return compA.getAlignment() == compB.getAlignment();
    }

    public void setLayoutManager(UILayout layout) {
        layoutManager = new UILayoutManager(layout);
    }

    public void setBackgroundImage(String path) {
        try {
            backgroundImg = ImageLoader.loadBufferedImage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBackgroundImage(BufferedImage image) {
        backgroundImg = image;
    }

    public void setBorder(ContainerBorder border) {
        this.border = border;
    }

    public ContainerBorder getBorder() {
        return border;
    }

    public void setPadding(ContainerPadding padding) {
        this.padding = padding;
    }

    public ContainerPadding getPadding() {
        return padding;
    }

    public void add(UIComponent component) {
        positionComponent(component);
        components.add(component);
        component.updatePosition();
        updateAllPositions(component);
    }

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

        // Redo the position components steps with all elements
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

    private void positionComponent(UIComponent component) {
        List<UIComponent> equalAlignmentComponents = findEqualAlignment(component);

        Point position = layoutManager.assignPositionsToComponents(getX(), getY(), getWidth(), getHeight(),
                border, padding,component, equalAlignmentComponents);

        component.setX(position.x);
        component.setY(position.y);
    }

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

//    @Override
//    public void offsetPosition(int offsetX, int offsetY){
//        setX(getX() + offsetX);
//        setY(getY() + offsetY);
//        offsetAllSubComponents(offsetX, offsetY);
//    }

//    private void offsetAllSubComponents(int offsetX, int offsetY) {
//        for (UIComponent component: getComponents()) {
//            component.offsetPosition(offsetX, offsetY);
//        }
//    }
}
