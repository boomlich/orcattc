package towerDefence.view.UI;

import java.awt.*;
import java.util.List;

public class UILayoutManager {
    private final UILayout layout;

    public UILayoutManager(UILayout layout) {
        this.layout = layout;
    }


    private int eggs1 (int value, boolean xAxis, int border, int padding ,List<UIComponent> components) {
        for (UIComponent equalComponent: components) {
            if (xAxis) {
                value += equalComponent.getWidth() + padding;
            } else {
                value += equalComponent.getHeight() + padding;
            }
        }

        return value + border;
    }

    protected Point assignPositionsToComponents(int x, int y, int width, int height, ContainerBorder border, ContainerPadding padding,
                                                UIComponent component, List<UIComponent> equalAlignmentComponents) {

        if (layout == UILayout.HORIZONTAL) {

            int westXValue = eggs1(x, true, border.west, padding.horizontal, equalAlignmentComponents);


            if (component.getAlignment() == UIAlignment.NORTH_WEST) {

                x = westXValue;
                y += border.north;
            }

            else if (component.getAlignment() == UIAlignment.WEST) {

                x = westXValue;
                y += height / 2 - component.getHeight() / 2;

            }

            else if (component.getAlignment() == UIAlignment.SOUTH_WEST) {

                x = westXValue;
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

            else if (component.getAlignment() == UIAlignment.SOUTH_EAST) {
                x += width;
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    x -= (equalComponent.getWidth() + padding.horizontal);
                }

                x -= (component.getWidth() + border.east);
                y += height - component.getHeight() - border.south;
            }
        }

        else {

            if (component.getAlignment() == UIAlignment.NORTH_WEST) {
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    y += equalComponent.getHeight() + padding.vertical;
                }

                x += border.west;
                y += border.north;
            }

            else if (component.getAlignment() == UIAlignment.WEST) {

                // Find total length of the horizontal sequence with the new component is included
                int length = component.getHeight();
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    length += equalComponent.getHeight() + padding.vertical;
                }

                // Distribute components evenly over the new length
                int currentY = (2 * y + height - length) / 2;
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    equalComponent.setY(currentY);
                    currentY += equalComponent.getHeight() + padding.vertical;
                }

                x += border.west;
                y = currentY;
            }

            else if (component.getAlignment() == UIAlignment.SOUTH_WEST) {
                y += height;
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    y -= (equalComponent.getHeight() + padding.vertical);
                }

                x += border.west;
                y -= (component.getHeight() + border.south);
            }

            else if (component.getAlignment() == UIAlignment.NORTH) {
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    y += equalComponent.getHeight() + padding.vertical;
                }

                x += width / 2 - component.getWidth() / 2;
                y += border.north;
            }

            else if (component.getAlignment() == UIAlignment.CENTER) {
                // Find total length of the horizontal sequence with the new component is included
                int length = component.getHeight();
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    length += equalComponent.getHeight() + padding.vertical;
                }

                // Distribute components evenly over the new length
                int currentY = (2 * y + height - length) / 2;
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    equalComponent.setY(currentY);
                    currentY += equalComponent.getHeight() + padding.vertical;
                }

                x += width/2 - component.getWidth() / 2;
                y = currentY;
            }

            else if (component.getAlignment() == UIAlignment.SOUTH) {
                y += height;
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    y -= (equalComponent.getHeight() + padding.vertical);
                }

                x += width/2 - component.getWidth() / 2;
                y -= (component.getHeight() + border.south);
            }

            else if (component.getAlignment() == UIAlignment.NORTH_EAST) {
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    y += equalComponent.getHeight() + padding.vertical;
                }

                x += width - component.getWidth() - border.south;
                y += border.north;
            }

            else if(component.getAlignment() == UIAlignment.EAST) {
                // Find total length of the horizontal sequence with the new component is included
                int length = component.getHeight();
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    length += equalComponent.getHeight() + padding.vertical;
                }

                // Distribute components evenly over the new length
                int currentY = (2 * y + height - length) / 2;
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    equalComponent.setY(currentY);
                    currentY += equalComponent.getHeight() + padding.vertical;
                }

                x += width - component.getWidth() - border.south;
                y = currentY;
            }

            else if (component.getAlignment() == UIAlignment.SOUTH_EAST) {
                y += height;
                for (UIComponent equalComponent: equalAlignmentComponents) {
                    y -= (equalComponent.getHeight() + padding.vertical);
                }

//                x += width - component.getWidth() - border.south;
                x += endCornerValue(width, component.getWidth(), border.south);
                y -= (component.getHeight() + border.south);
            }
        }

        return new Point(x, y);
    }


    private int endCornerValue(int size, int componentSize, int border) {
        return size - componentSize - border;
    }
}
