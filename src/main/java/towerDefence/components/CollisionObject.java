package towerDefence.components;

import java.awt.geom.Point2D;

public interface CollisionObject {

    public Point2D getPosition();

    public Collision getCollision();

}