package towerDefence.components.collision;

import java.awt.geom.Point2D;

public interface CollisionObject {

    /**
     * @return position of the object that will be checked for
     * collision. Used the distance between the collision
     * object and this position to determine if  a collision
     * takes place.
     */
    public Point2D getPosition();

    /**
     * @return collision circle of the object
     */
    public Collision getCollision();

}
