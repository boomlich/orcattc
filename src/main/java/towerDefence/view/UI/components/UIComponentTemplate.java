package towerDefence.view.UI.components;

import java.awt.*;
import java.util.List;

public abstract class UIComponentTemplate implements UIComponent{

    private int x = 0;
    private int y = 0;
    private int width;
    private int height;
    private UIAlignment alignment = UIAlignment.CENTER;

    public UIComponentTemplate(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setAlignment(UIAlignment alignment) {
        this.alignment = alignment;
    }

    @Override
    public UIAlignment getAlignment() {
        return alignment;
    }

    @Override
    public void paint(Graphics2D g2D) {

    }

    @Override
    public void updatePosition() {
    }

    @Override
    public List<UIComponent> getComponents() {
        return null;
    }

    @Override
    public void updateAllPositions(UIComponent component) {
    }

    @Override
    public void setAllSubComponentsInactive(UIComponent component){

    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void offsetPosition(int offsetX, int offsetY) {
        setX(getX() + offsetX);
        setY(getY() + offsetY);
        offsetAllSubComponents(offsetX, offsetY);
    }


    private void offsetAllSubComponents(int offsetX, int offsetY) {
        if (getComponents() != null) {
            for (UIComponent component: getComponents()) {
                component.offsetPosition(offsetX, offsetY);
            }
        }
    }
}
