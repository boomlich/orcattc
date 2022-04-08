package towerDefence.tower;

import java.awt.geom.Point2D;

public interface ITower {

    /**
     * Upgrade the rank of the tower by 1.
     */
    public void updateRank();

    public void update(double deltaSteps);

    public void updatePosition(Point2D newPosition);
}
