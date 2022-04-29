package towerDefence.components.movement;

import towerDefence.math.MathHelperMethods;
import towerDefence.level.path.SplinePathData;

import java.awt.geom.Point2D;

public class SplineMovement{

    private final SplinePathData path;

    /**
     * Movementspeed on the path
     */
    private double speed;

    /**
     * Current point on the path
     */
    private int currentIndex;

    /**
     * Current length traversed on the path
     */
    private double currentLength;

    public SplineMovement(SplinePathData path, double speed) {
        this.path = path;
        this.speed = speed;
        currentIndex = 0;
        currentLength = 0;
    }


    public void update(double deltaSteps) {
        if (!movementDone()) {
            currentLength += deltaSteps * speed;
        }
    }


    public Point2D getPosition() {
        return getPointOnSelectedPathLength(currentLength);
    }

    public double getPathProgression() {
        return currentLength / path.getTotalLength();
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Take the current lengt and find the position on the path. The position is
     * between two spline control points and the percentage of progress between the
     * two control points. If it returns 10.5, it means that the point is located
     * between spline control point 10 and 11, specifically 50% of the way from 10 to 11.
     *
     * @param currentLength current length traversed on the path
     * @return Normalized offset, where it gives the spline point and the
     */
    private double getNormalisedOffset(double currentLength) {
        int i = 0;
        while (currentLength > path.getSegmentLength().get(i)) {
            currentLength -= path.getSegmentLength().get(i);
            i ++;
        }

        return i + (currentLength / path.getSegmentLength().get(i));
    }

    /**
     * Find the position of a point at a spesific length on the path. Converts
     * the length to a normalized offset, which gives what spline controll points
     * the point is located and the progress between those spline points in percentage.
     * Update the currentIndex to give the point.
     *
     * @param currentLength
     * @return
     */
    private Point2D getPointOnSelectedPathLength(double currentLength) {
        currentLength = getNormalisedOffset(currentLength);

        int segment = (int) currentLength;
        double segmentRemainder = currentLength - segment;
        double segmentPoint = path.getSegmentResolution() * segmentRemainder;
        int segmentPointIndex = (int) segmentPoint;
        currentIndex = (segment * path.getSegmentResolution() + segmentPointIndex);

        return MathHelperMethods.getSplinePoint(currentLength, path.getSplineControls());
    }

    /**
     * @return angle of orientation for the path point in radians. Calculated
     * based of two points on the path
     */
    public double getRotation() {
        return path.getPathPoints().get(currentIndex).rotation;
    }

    /**
     * @return normal vector to the path. Used to offset path movement by k-factor
     */
    public Point2D getUnitNormalVector() {
        return path.getPathPoints().get(currentIndex).unitNormalVector;
    }

    /**
     * @return true if movement is done, reached the end of the path.
     */
    public boolean movementDone() {
        return currentIndex > path.getPathPoints().size() - 2;
    }
}