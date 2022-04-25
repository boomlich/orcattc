package towerDefence.view.UI.components;

import java.awt.*;
import java.util.List;

public interface UIComponent {

    public int getWidth();

    public int getHeight();

    public void setWidth(int width);

    public void setHeight(int height);

    public int getX();

    public int getY();

    public void setX(int x);

    public void setY(int y);

    public UIAlignment getAlignment();

    public void setAlignment(UIAlignment alignment);

    public void paint(Graphics2D g2D);

    public void updatePosition();

    public List<UIComponent> getComponents();

    public void updateAllPositions(UIComponent component);

    public void setAllSubComponentsInactive(UIComponent component);

    public void offsetPosition(int offsetX, int offsetY);

}
