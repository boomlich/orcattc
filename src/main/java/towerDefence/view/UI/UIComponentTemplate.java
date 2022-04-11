package towerDefence.view.UI;

import java.awt.*;

public class UIComponentTemplate implements UIComponent{

    private int x = 0;
    private int y = 0;
    private final int width, height;
    private UIAlignment alignment = UIAlignment.CENTER;
    private UILayout layout = UILayout.HORIZONTAL;

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
    public int getHeight() {
        return height;
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



}
