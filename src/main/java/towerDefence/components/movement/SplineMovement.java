package towerDefence.components.movement;

import towerDefence.Math.MathHelperMethods;
import towerDefence.level.path.PathPoint;
import towerDefence.level.path.SplinePathData;

import java.awt.geom.Point2D;
import java.util.List;

public class SplineMovement implements IMovement{

    private final SplinePathData path;
    private final double speed;
    private int currentIndex;
    private double currentLength;

    public SplineMovement(SplinePathData path, double speed) {
        this.path = path;
        this.speed = speed;
        currentIndex = 0;
        currentLength = 0;
    }

    @Override
    public void update(double deltaSteps) {
        if (!movementDone()) {
            currentLength += deltaSteps * speed;
        }
    }

    @Override
    public Point2D getPosition() {
        return calculateNormalizedPosition(currentLength);
    }

    @Override
    public double getPathProgression() {
        return currentLength / path.getTotalLength();
    }

    private double getNormalisedOffset(double currentLength) {
        int i = 0;
        while (currentLength > path.getSegmentLength().get(i)) {
            currentLength -= path.getSegmentLength().get(i);
            i ++;
        }

        return i + (currentLength / path.getSegmentLength().get(i));
    }

    protected Point2D calculateNormalizedPosition (double currentLength) {
        currentLength = getNormalisedOffset(currentLength);

        int segment = (int) currentLength;
        double segmentRemainder = currentLength - segment;
        double segmentPoint = path.getSegmentResolution() * segmentRemainder;
        int segmentPointIndex = (int) segmentPoint;
        currentIndex = (segment * path.getSegmentResolution() + segmentPointIndex);

        return MathHelperMethods.getSplinePoint(currentLength, path.getSplineControls());
    }

    /**
     * @return angle of orientation in radians
     */
    public double getRotation() {
        return path.getPathPoints().get(currentIndex).rotation;
    }

    public Point2D getUnitNormalVector() {
        return path.getPathPoints().get(currentIndex).unitNormalVector;
    }

    public boolean movementDone() {
        return currentIndex > path.getPathPoints().size() - 2;
    }
}