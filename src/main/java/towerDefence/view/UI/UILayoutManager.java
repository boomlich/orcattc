package towerDefence.view.UI;

import java.awt.*;
import java.util.List;

public class UILayoutManager {
    private final UILayout layout;

    public UILayoutManager(UILayout layout) {
        this.layout = layout;
    }


    private int calcWestXAndTopYValue(int value, boolean xAxis, int border, int padding , List<UIComponent> components) {
        for (UIComponent equalComponent: components) {
            if (xAxis) {
                value += equalComponent.getWidth() + padding;
            } else {
                value += equalComponent.getHeight() + padding;
            }
        }
        return value + border;
    }


    private int calculateCenterXValue(int value, boolean xAxis, int size, int componentSize, int padding, List<UIComponent> components) {

        // Find total length of the horizontal sequence with the new component is included
        int length = componentSize;
        for (UIComponent equalComponent: components) {
            length += equalComponent.getWidth() + padding;
        }

        // Distribute components evenly over the new length
        int currentX = (2 * value + size - length) / 2;
        for (UIComponent equalComponent: components) {
            equalComponent.setX(currentX);
            if (xAxis) {
                currentX += equalComponent.getWidth() + padding;
            } else {
                currentX += equalComponent.getHeight() + padding;
            }
        }

        return currentX;
    }

    private int calculateEastXValue(int value, int containerSize, int componentSize, boolean xAxis, int padding, int border, List<UIComponent> components) {
        value += containerSize;
        for (UIComponent equalComponent: components) {
            if (xAxis) {
                value -= (equalComponent.getWidth() + padding);
            } else {
                value -= (equalComponent.getHeight() + padding);
            }
        }
        return value - (componentSize + border);
    }

    protected Point assignPositionsToComponents(int x, int y, int width, int height, ContainerBorder border, ContainerPadding padding,
                                                UIComponent component, List<UIComponent> equalAlignmentComponents) {

        if (layout == UILayout.HORIZONTAL) {

            int topYValue = y + border.north;
            int midYValue = y + height / 2 - component.getHeight() / 2;
            int botYValue = y + height - component.getHeight() - border.south;


            if (component.getAlignment() == UIAlignment.NORTH_WEST) {
                x = calcWestXAndTopYValue(x, true, border.west, padding.horizontal, equalAlignmentComponents);
                y = topYValue;
            }
            else if (component.getAlignment() == UIAlignment.WEST) {
                x = calcWestXAndTopYValue(x, true, border.west, padding.horizontal, equalAlignmentComponents);
                y = midYValue;
            }
            else if (component.getAlignment() == UIAlignment.SOUTH_WEST) {
                x = calcWestXAndTopYValue(x, true, border.west, padding.horizontal, equalAlignmentComponents);
                y = botYValue;
            }
            else if (component.getAlignment() == UIAlignment.NORTH) {
                x = calculateCenterXValue(x, true, width, component.getWidth(), padding.horizontal, equalAlignmentComponents);
                y = topYValue;
            }
            else if (component.getAlignment() == UIAlignment.CENTER) {
                x = calculateCenterXValue(x, true, width, component.getWidth(), padding.horizontal, equalAlignmentComponents);
                y = midYValue;
            }
            else if (component.getAlignment() == UIAlignment.SOUTH) {
                x = calculateCenterXValue(x, true, width, component.getWidth(), padding.horizontal, equalAlignmentComponents);
                y = botYValue;
            }
            else if (component.getAlignment() == UIAlignment.NORTH_EAST) {
                x = calculateEastXValue(x, width, component.getWidth(), true, padding.horizontal, border.east, equalAlignmentComponents);
                y = topYValue;
            }
            else if (component.getAlignment() == UIAlignment.EAST) {
                x = calculateEastXValue(x, width, component.getWidth(), true, padding.horizontal, border.east, equalAlignmentComponents);
                y = midYValue;
            }
            else if (component.getAlignment() == UIAlignment.SOUTH_EAST) {
                x = calculateEastXValue(x, width, component.getWidth(), true, padding.horizontal, border.east, equalAlignmentComponents);
                y = botYValue;
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
