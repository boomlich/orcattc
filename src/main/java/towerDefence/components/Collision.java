package towerDefence.components;

import towerDefence.enemies.IEnemy;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Collision extends Ellipse2D {
    private final double radius;

    public Collision(double radius) {
        this.radius = radius;
    }

    public IEnemy checkCollision () {
        return null;
    }

    public double getRadius() {
        return radius;
    }



    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {

    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }
}
