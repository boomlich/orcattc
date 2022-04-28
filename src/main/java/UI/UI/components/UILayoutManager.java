package UI.UI.components;

import java.awt.*;
import java.util.List;

public class UILayoutManager {
    private final UILayout layout;

    public UILayoutManager(UILayout layout) {
        this.layout = layout;
    }

    /**
     * Calculate the x value of the components' in the first row (West) if xAxis set to true.
     * Calculate the y value of the components' int the first column (North) if xAxis set to false.
     *
     *
     * @param value x or y value
     * @param xAxis the target axis for calculation
     * @param border container border
     * @param padding horizontal or vertical padding between components
     * @param components all components with equal alignment
     * @return position of new component
     */
    private int calcAxisFirstRowOrColumn(int value, boolean xAxis, int border, int padding , List<UIComponent> components) {
        for (UIComponent equalComponent: components) {
            if (xAxis) {
                value += equalComponent.getWidth() + padding;
            } else {
                value += equalComponent.getHeight() + padding;
            }
        }
        return value + border;
    }

    /**
     * Calculate the x-value and distribute the components in the center row if
     * xAxis set to true. Calculate the y-value and distribute the components
     * in the center column if xAxis is set to false.
     *
     * @param value x or y value
     * @param xAxis the target axis for calculation and distribution
     * @param containerSize total size of the container in the target axis (width or height)
     * @param componentSize size of the container in the target axis (width or height)
     * @param padding horizontal or vertical padding between components
     * @param components all components with equal alignment
     * @return position of new component
     */
    private int distributeCenterComponents(int value, boolean xAxis, int containerSize, int componentSize, int padding, List<UIComponent> components) {

        // Find total length of the horizontal sequence with the new component is included
        int length = componentSize;
        for (UIComponent equalComponent: components) {
            if (xAxis) {
                length += equalComponent.getWidth() + padding;
            } else {
                length += equalComponent.getHeight() + padding;
            }
        }

        // Distribute components evenly over the new length
        int currentValue = (2 * value + containerSize - length) / 2;
        for (UIComponent equalComponent: components) {
            if (xAxis) {
                equalComponent.setX(currentValue);
                currentValue += equalComponent.getWidth() + padding;
            } else {
                equalComponent.setY(currentValue);
                currentValue += equalComponent.getHeight() + padding;
            }
        }
        return currentValue;
    }

    /**
     * Calculate the x value of the components' in the last row (EAST) if xAxis set to true.
     * Calculate the y value of the components' int the last column (SOUTH) if xAxis set to false.
     *
     * @param value x or y value
     * @param containerSize total size of the container in the target axis (width or height)
     * @param componentSize size of the container in the target axis (width or height)
     * @param xAxis the target axis for calculation and distribution
     * @param padding horizontal or vertical padding between components
     * @param border container border
     * @param components all components with equal alignment
     * @return position of new component
     */
    private int calcAxisLastRowOrColumn(int value, int containerSize, int componentSize, boolean xAxis, int padding, int border, List<UIComponent> components) {
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

    /**
     * Calculate position of newly added component and potentially redistribute the
     * other components. Calculation is based of the currently active layout and
     * the component(s) selected alignment. If two components have equal alignment
     * they will be placed next to each other, seperated by the active padding.
     *
     * If HORIZONTAL layout is selected and the horizontal alignment is set to WEST
     * or EAST, the first component will be positioned furthest to the WEST/EAST,
     * and the subsequent equally aligned components will be stacked next to it
     *
     * If HORIZONTAL alignment is set to CENTER, the components will be stacked
     * horizontally and distributed equally around the center-width of the container.
     *
     * If VERTICAL layout is selected and the vertical alignment is set to NORTH
     * or SOUTH, the first component will be positioned furthers to the NORTH/SOUTH
     * and the subsequent equally aligned components will be stacked beneath or on top.
     *
     * If VERTICAL layout is set to CENTER, the components will be stacked vertically and
     * distributed equally around the center-height of the container
     *
     * @param x x-coordinate of the container
     * @param y y-coordinate of the container
     * @param width total width of the container
     * @param height total height of the container
     * @param border border of the container
     * @param padding padding of the contaner
     * @param component new component to be added
     * @param equalAlignmentComponents all components with equal alignment
     * @return position of new component
     */
    protected Point assignPositionsToComponents(int x, int y, int width, int height, ContainerBorder border, ContainerPadding padding,
                                                UIComponent component, List<UIComponent> equalAlignmentComponents) {

        if (layout == UILayout.HORIZONTAL) {

            int topYValue = y + border.north;
            int midYValue = y + height / 2 - component.getHeight() / 2;
            int botYValue = y + height - component.getHeight() - border.south;

            if (component.getAlignment() == UIAlignment.NORTH_WEST) {
                x = calcAxisFirstRowOrColumn(x, true, border.west, padding.horizontal, equalAlignmentComponents);
                y = topYValue;
            }
            else if (component.getAlignment() == UIAlignment.WEST) {
                x = calcAxisFirstRowOrColumn(x, true, border.west, padding.horizontal, equalAlignmentComponents);
                y = midYValue;
            }
            else if (component.getAlignment() == UIAlignment.SOUTH_WEST) {
                x = calcAxisFirstRowOrColumn(x, true, border.west, padding.horizontal, equalAlignmentComponents);
                y = botYValue;
            }
            else if (component.getAlignment() == UIAlignment.NORTH) {
                x = distributeCenterComponents(x, true, width, component.getWidth(), padding.horizontal, equalAlignmentComponents);
                y = topYValue;
            }
            else if (component.getAlignment() == UIAlignment.CENTER) {
                x = distributeCenterComponents(x, true, width, component.getWidth(), padding.horizontal, equalAlignmentComponents);
                y = midYValue;
            }
            else if (component.getAlignment() == UIAlignment.SOUTH) {
                x = distributeCenterComponents(x, true, width, component.getWidth(), padding.horizontal, equalAlignmentComponents);
                y = botYValue;
            }
            else if (component.getAlignment() == UIAlignment.NORTH_EAST) {
                x = calcAxisLastRowOrColumn(x, width, component.getWidth(), true, padding.horizontal, border.east, equalAlignmentComponents);
                y = topYValue;
            }
            else if (component.getAlignment() == UIAlignment.EAST) {
                x = calcAxisLastRowOrColumn(x, width, component.getWidth(), true, padding.horizontal, border.east, equalAlignmentComponents);
                y = midYValue;
            }
            else if (component.getAlignment() == UIAlignment.SOUTH_EAST) {
                x = calcAxisLastRowOrColumn(x, width, component.getWidth(), true, padding.horizontal, border.east, equalAlignmentComponents);
                y = botYValue;
            }
        }

        else {

            int leftXValue = x + border.west;
            int midXValue = x + width / 2 - component.getWidth() / 2;
            int rightXValue = x + width - component.getWidth() - border.south;

            if (component.getAlignment() == UIAlignment.NORTH_WEST) {
                x = leftXValue;
                y = calcAxisFirstRowOrColumn(y, false, border.north, padding.vertical, equalAlignmentComponents);
            }
            else if (component.getAlignment() == UIAlignment.WEST) {
                x = leftXValue;
                y = distributeCenterComponents(y, false, height, component.getHeight(), padding.vertical, equalAlignmentComponents);
            }
            else if (component.getAlignment() == UIAlignment.SOUTH_WEST) {
                x = leftXValue;
                y = calcAxisLastRowOrColumn(y, height, component.getHeight(), false, padding.vertical, border.south, equalAlignmentComponents);
            }
            else if (component.getAlignment() == UIAlignment.NORTH) {
                x = midXValue;
                y = calcAxisFirstRowOrColumn(y, false, border.north, padding.vertical, equalAlignmentComponents);
            }
            else if (component.getAlignment() == UIAlignment.CENTER) {
                x = midXValue;
                y = distributeCenterComponents(y, false, height, component.getHeight(), padding.vertical, equalAlignmentComponents);
            }
            else if (component.getAlignment() == UIAlignment.SOUTH) {
                x = midXValue;
                y = calcAxisLastRowOrColumn(y, height, component.getHeight(), false, padding.vertical, border.south, equalAlignmentComponents);
            }
            else if (component.getAlignment() == UIAlignment.NORTH_EAST) {
                x = rightXValue;
                y = calcAxisFirstRowOrColumn(y, false, border.north, padding.vertical, equalAlignmentComponents);
            }
            else if(component.getAlignment() == UIAlignment.EAST) {
                x = rightXValue;
                y = distributeCenterComponents(y, false, height, component.getHeight(), padding.vertical, equalAlignmentComponents);
            }
            else if (component.getAlignment() == UIAlignment.SOUTH_EAST) {
                x = rightXValue;
                y = calcAxisLastRowOrColumn(y, height, component.getHeight(), false, padding.vertical, border.south, equalAlignmentComponents);
            }
        }

        return new Point(x, y);
    }
}
