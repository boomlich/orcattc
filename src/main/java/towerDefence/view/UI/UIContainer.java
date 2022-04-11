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
        layoutManager = new UILayoutManager(UILayout.HORIZONTAL);
    }

    private void positionComponent(UIComponent component) {
        List<UIComponent> equalAlignmentComponents = findEqualAlignment(component);

        Point position = layoutManager.assignPositionsToComponents(getX(), getY(), getWidth(), getHeight(),
                border, padding,component, equalAlignmentComponents);

        component.setX(position.x);
        component.setY(position.y);


//        int x = getX();
//        int y = getY();
//
//        if (layout == UILayout.HORIZONTAL) {
//            if (component.getAlignment() == UIAlignment.NORTH_WEST) {
//
//                for (UIComponent equalComponent: equalAlignmentComponents) {
//                    x += equalComponent.getWidth() + padding.horizontal;
//                }
//
//                x += border.west;
//                y += border.north;
//            }
//
//            else if (component.getAlignment() == UIAlignment.WEST) {
//
//                for (UIComponent equalComponent: equalAlignmentComponents) {
//                    x += equalComponent.getWidth() + padding.horizontal;
//                }
//
//                x += border.west;
//                y += getHeight()/2 - component.getHeight() / 2;
//
//            }
//
//            else if (component.getAlignment() == UIAlignment.SOUTH_WEST) {
//
//                for (UIComponent equalComponent: equalAlignmentComponents) {
//                    x += equalComponent.getWidth() + padding.horizontal;
//                }
//
//                x += border.west;
//                y += getHeight() - component.getHeight() - border.south;
//            }
//
//            else if (component.getAlignment() == UIAlignment.NORTH) {
//
//                // Find total length of the horizontal sequence with the new component is included
//                int length = component.getWidth();
//                for (UIComponent equalComponent: equalAlignmentComponents) {
//                    length += equalComponent.getWidth() + padding.horizontal;
//                }
//
//                // Distribute components evenly over the new length
//                int currentX = (2 * x + getWidth() - length) / 2;
//                for (UIComponent equalComponent: equalAlignmentComponents) {
//                    equalComponent.setX(currentX);
//                    currentX += equalComponent.getWidth() + padding.horizontal;
//                }
//
//                x = currentX;
//                y += border.north;
//            }
//
//            else if (component.getAlignment() == UIAlignment.CENTER) {
//                // Find total length of the horizontal sequence with the new component is included
//                int length = component.getWidth();
//                for (UIComponent equalComponent: equalAlignmentComponents) {
//                    length += equalComponent.getWidth() + padding.horizontal;
//                }
//
//                // Distribute components evenly over the new length
//                int currentX = (2 * x + getWidth() - length) / 2;
//                for (UIComponent equalComponent: equalAlignmentComponents) {
//                    equalComponent.setX(currentX);
//                    currentX += equalComponent.getWidth() + padding.horizontal;
//                }
//
//                x = currentX;
//                y += getHeight()/2 - component.getHeight() / 2;
//
//            }
//
//            else if (component.getAlignment() == UIAlignment.SOUTH) {
//                // Find total length of the horizontal sequence with the new component is included
//                int length = component.getWidth();
//                for (UIComponent equalComponent: equalAlignmentComponents) {
//                    length += equalComponent.getWidth() + padding.horizontal;
//                }
//
//                // Distribute components evenly over the new length
//                int currentX = (2 * x + getWidth() - length) / 2;
//                for (UIComponent equalComponent: equalAlignmentComponents) {
//                    equalComponent.setX(currentX);
//                    currentX += equalComponent.getWidth() + padding.horizontal;
//                }
//
//                x = currentX;
//                y += getHeight() - component.getHeight() - border.south;
//            }
//
//            else if (component.getAlignment() == UIAlignment.NORTH_EAST) {
//
//                x += getWidth();
//                for (UIComponent equalComponent: equalAlignmentComponents) {
//                    x -= (equalComponent.getWidth() + padding.horizontal);
//                }
//
//                x -= (component.getWidth() + border.east);
//                y += border.north;
//            }
//
//            else if (component.getAlignment() == UIAlignment.EAST) {
//
//                x += getWidth();
//                for (UIComponent equalComponent: equalAlignmentComponents) {
//                    x -= (equalComponent.getWidth() + padding.horizontal);
//                }
//
//                x -= (component.getWidth() + border.east);
//                y += getHeight()/2 - component.getHeight() / 2;
//            }
//
//        }
//
//        component.setX(x);
//        component.setY(y);
    }

    public void setBackground(Color color) {
        background = color;
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
