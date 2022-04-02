package towerDefence.enemies;

import java.awt.geom.Point2D;

public interface IEnemy {

    public void update(double deltaSteps);

    public Point2D getPosition();
}
