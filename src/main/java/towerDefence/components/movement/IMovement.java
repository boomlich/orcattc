package towerDefence.components.movement;

import java.awt.geom.Point2D;

public interface IMovement {

    public void update(double deltaSteps);

    public Point2D getPosition();
}
