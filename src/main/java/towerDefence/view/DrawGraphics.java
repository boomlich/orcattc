package towerDefence.view;

import towerDefence.view.sprite.Sprite;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class DrawGraphics {


    public static void drawSprite(Graphics2D g2D, Sprite sprite, Point2D coordinate) {

        AffineTransform reset = g2D.getTransform();

        g2D.rotate(sprite.getRotation(), coordinate.getX(), coordinate.getY());
        g2D.translate(coordinate.getX(), coordinate.getY());
        g2D.drawImage(sprite.image, 0, 0, sprite.width, sprite.height, null);
        g2D.setTransform(reset);
    }
}
