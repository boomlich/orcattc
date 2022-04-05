package towerDefence.components.movement;

import towerDefence.level.path.PathPoint;

import java.awt.geom.Point2D;
import java.util.List;

public class SplineMovement implements IMovement{

    private final List<PathPoint> splinePoints;
    private final double speed;
    private double currentIndex;

    public SplineMovement(List<PathPoint> splinePoints, double speed) {
        this.splinePoints = splinePoints;
        this.speed = speed;
        currentIndex = 0;
    }

    @Override
    public void update(double deltaSteps) {
        if (!movementDone()) {
            currentIndex += deltaSteps * speed;
        }
    }

    @Override
    public Point2D getPosition () {
        return splinePoints.get((int) currentIndex).coordinate;
    }

    /**
     * @return angle of orientation in radians
     */
    public double getRotation() {
        return splinePoints.get((int) currentIndex).rotation;
    }

    public boolean movementDone() {
        return currentIndex > splinePoints.size() - 2;
    }
}
