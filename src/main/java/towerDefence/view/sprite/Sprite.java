package towerDefence.view.sprite;


import java.awt.image.BufferedImage;


public class Sprite {
    public final BufferedImage image;
    public final int width, height;
    private double rotation;

    public Sprite(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        rotation = 0;
    }

    protected void setRotation(double angle) {
        rotation = angle;
    }

    public double getRotation() {
        return rotation;
    }
}
