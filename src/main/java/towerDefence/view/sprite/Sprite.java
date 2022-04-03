package towerDefence.view.sprite;


import java.awt.image.BufferedImage;


public class Sprite {
    public final BufferedImage image;
    public final int width, height;

    public Sprite(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
    }
}
