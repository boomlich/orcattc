package towerDefence.view.UI;

import java.util.List;

public class UILayoutManager {
    private final UILayout layout;

    public UILayoutManager(UILayout layout) {
        this.layout = layout;
    }

    protected void assignPositionsToComponents (int x, int y, int width, int height, ContainerBorder border, ContainerPadding padding,
                                                UIComponent component, List<UIComponent> equalAlignmentComponents) {

        if (layout == UILayout.HORIZONTAL) {
            if (component.getAlignment() == UIAlignment.NORTH_WEST) {

                for (UIComponent equalComponent: equalAlignmentComponents) {
                    x += equalComponent.getWidth() + padding.horizontal;
                }

                x += border.west;
                y += border.north;
            }

            else if (component.getAlignment() == UIAlignment.WEST) {

                for (UIComponent equalComponent: equalAlignmentComponents) {
                    x += equalComponent.getWidth() + padding.horizontal;
                }

                x += border.west;
                y += height / 2 - component.getHeight() / 2;

            }

            else if (component.getAlignment() == UIAlignment.SOUTH_WEST) {

                for (UIComponent equalComponent: equalAlignmentComponents) {
                    x += equalComponent.getWidth() + padding.horizontal;
                }

                x += border.west;
                y += height - component.getHeight() - border.south;
            }

            else if (component.getAlignment() == UIAlignment.NORTH) {

                // Find total length of the horizontal sequence with the new component is included
                int length = component.getWidth();
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    length += equalComponent.getWidth() + padding.horizontal;
                }

                // Distribute components evenly over the new length
                int currentX = (2 * x + width - length) / 2;
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    equalComponent.setX(currentX);
                    currentX += equalComponent.getWidth() + padding.horizontal;
                }

                x = currentX;
                y += border.north;
            }

            else if (component.getAlignment() == UIAlignment.CENTER) {
                // Find total length of the horizontal sequence with the new component is included
                int length = component.getWidth();
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    length += equalComponent.getWidth() + padding.horizontal;
                }

                // Distribute components evenly over the new length
                int currentX = (2 * x + width - length) / 2;
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    equalComponent.setX(currentX);
                    currentX += equalComponent.getWidth() + padding.horizontal;
                }

                x = currentX;
                y += height / 2 - component.getHeight() / 2;

            }

            else if (component.getAlignment() == UIAlignment.SOUTH) {
                // Find total length of the horizontal sequence with the new component is included
                int length = component.getWidth();
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    length += equalComponent.getWidth() + padding.horizontal;
                }

                // Distribute components evenly over the new length
                int currentX = (2 * x + width - length) / 2;
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    equalComponent.setX(currentX);
                    currentX += equalComponent.getWidth() + padding.horizontal;
                }

                x = currentX;
                y += height - component.getHeight() - border.south;
            }

            else if (component.getAlignment() == UIAlignment.NORTH_EAST) {

                x += width;
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    x -= (equalComponent.getWidth() + padding.horizontal);
                }

                x -= (component.getWidth() + border.east);
                y += border.north;
            }

            else if (component.getAlignment() == UIAlignment.EAST) {

                x += width;
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    x -= (equalComponent.getWidth() + padding.horizontal);
                }

                x -= (component.getWidth() + border.east);
                y += height/2 - component.getHeight() / 2;
            }
        }
        component.setX(x);
        component.setY(y);
    }
}
