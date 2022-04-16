package towerDefence.components;

import towerDefence.Math.MathHelperMethods;
import towerDefence.view.IRenderableObject;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Collision implements CollisionObject {
    private final double radius;

    /** Center of collision circle */
    private Point2D position = new Point2D.Double(0,0);

    private final boolean isSingleTarget;

    public Collision(double radius, boolean isSingleTarget) {
        this.radius = radius;
        this.isSingleTarget = isSingleTarget;
    }

    public Collision(double radius) {
        this(radius, false);
    }

    /**
     * Sweap and prune algorithm for collision detection. Filter out potential
     * objects that is outside the x-values, before checking collision. If
     * only single-target selected, the first detected collision target is returned.
     *
     * @param targets potential objects
     * @return List of all objects collided with
     */
    public <T extends CollisionObject> List<T> updateCollision(List<T> targets) {
        List<T> detectedTargets = new ArrayList<>();

        for (T target: targets) {
            // Filter out enemies outside of bounds
            if (inBounds(target)) {
                if (collisionDetected(target)) {
                    detectedTargets.add(target);
                    if (isSingleTarget) {
                        return detectedTargets;
                    }
                }
            }
        }
        return detectedTargets;
    }

    private boolean inBounds(CollisionObject target) {
        double targetMinX = target.getCollision().getPosition().getX() - target.getCollision().getRadius();
        double targetMaxX = target.getCollision().getPosition().getX() + target.getCollision().getRadius();
        return targetMinX < position.getX() + radius && targetMaxX > position.getX() - radius;
    }

    private boolean collisionDetected(CollisionObject target) {
        double distance = MathHelperMethods.vectorLength(target.getCollision().getPosition(), this.position);
        return distance < target.getCollision().getRadius() + this.radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public Collision getCollision() {
        return this;
    }

}
