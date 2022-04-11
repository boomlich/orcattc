package towerDefence.view.UI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIContainer extends UIComponentTemplate{

    private final List<UIComponent> components = new ArrayList<>();

    private ContainerBorder border = new ContainerBorder(0);
    private ContainerPadding padding = new ContainerPadding(0);
    private Color background;

    private UILayoutManager layoutManager;

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
        }
        g2D.fill(new Rectangle(getX(), getY(), getWidth(), getHeight()));


        for (UIComponent component: components) {
            component.paint(g2D);
        }
    }
}
