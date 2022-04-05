package towerDefence.components.movement;

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
//            System.out.println(currentLength);
//            currentIndex += deltaSteps * speed;
        }
    }

    @Override
    public Point2D getPosition() {

        double normalisedOffset = getNormalisedOffset(currentLength);


//        System.out.println(getNormalisedOffset(currentLength));
        return calculateNormalizedPosition(currentLength);
    }

    private void getPoint() {

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
        double segmentPointRemainder = segmentPoint - segmentPointIndex;


        currentIndex = (segment * path.getSegmentResolution() + segmentPointIndex);

        Point2D pointCoordinate = path.getPathPoints().get(currentIndex).coordinate;
        Point2D pointDirection = path.getPathPoints().get(currentIndex).direction;


        double x = pointCoordinate.getX() + pointDirection.getX() * segmentPointRemainder;
        double y = pointCoordinate.getY() + pointDirection.getY() * segmentPointRemainder;

        return new Point2D.Double(x, y);

//        return path.getPathPoints().get((int) currentIndex).coordinate;
    }

    /**
     * @return angle of orientation in radians
     */
    public double getRotation() {
        return path.getPathPoints().get(currentIndex).rotation;
    }

    public boolean movementDone() {
        return currentIndex > path.getPathPoints().size() - 2;
    }
}

/**
 *
 *         double segmentLength = 0.0;
 *         int i = 0;
 *         double remainder = 0.0;
 *         while (currentLength >= 0 && i < path.getSegmentLength().size()) {
 *             remainder = currentLength;
 *             segmentLength = path.getSegmentLength().get(i);
 *             currentLength -= segmentLength;
 *             i ++;
 *         }
 *         i --;
 *
 *
 */
