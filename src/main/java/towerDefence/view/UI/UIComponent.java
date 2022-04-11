package towerDefence.view.UI;

import java.awt.*;

public interface UIComponent {

    public int getWidth();

    public int getHeight();

    public int getX();

    public int getY();

    public void setX(int x);

    public void setY(int y);

    public UIAlignment getAlignment();

    public void setAlignment(UIAlignment alignment);

    public void paint(Graphics2D g2D);

}
