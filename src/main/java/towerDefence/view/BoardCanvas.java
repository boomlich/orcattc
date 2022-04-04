package towerDefence.view;

import towerDefence.components.movement.LinearMovement;
import towerDefence.enemies.IEnemy;
import towerDefence.enemies.enemyTypes.RowBoat;
import towerDefence.level.path.TrackPath;
import towerDefence.view.sprite.Sprite;
import towerDefence.view.sprite.SpriteEngine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BoardCanvas implements ICanvas{

    GameRenderable gameModel;

    Point2D pointA = new Point2D.Double(200, 200);
    Point2D pointB = new Point2D.Double(150, 100);

//    IEnemy testEnemy = new RowBoat(new LinearMovement(1, pointA, pointB));



    BufferedImage img;
//
//    {
//        try {
//            img = ImageIO.read(getClass().getResource("enemies/Sprite_RowBoat.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    List<Point2D> splineControls = new ArrayList<>(Arrays.asList(
            new Point2D.Double(100, 125),
            new Point2D.Double(157, 166),
            new Point2D.Double(200, 100),
            new Point2D.Double(300, 90),
            new Point2D.Double(341, 134),
            new Point2D.Double(305, 181),
            new Point2D.Double(256, 144)
    ));

    TrackPath path = new TrackPath(splineControls);

    SpriteEngine spriteAnim = new SpriteEngine("TestSpriteSheet.png", 4, 5, 15, 0);

    public BoardCanvas(GameRenderable gameModel) {
        this.gameModel = gameModel;

        spriteAnim.start(0, 18, true);

        try {
            img = ImageIO.read(ClassLoader.getSystemResource( "rowboat.png" ));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    @Override
    public void paint(Graphics2D g2D) {

        

//        g2D.fill(new Rectangle2D.Double(testEnemy.getPosition().getX(), testEnemy.getPosition().getY(), 50, 50));
//
//        testEnemy.update(1);

//        AffineTransform reset = g2D.getTransform();
//        g2D.rotate(Math.toRadians(-90),132, 132);
//        g2D.drawImage(img, 100, 100, 64, 64, null);
//        g2D.setTransform(reset);

        drawSprite(g2D, spriteAnim.getSprite(), Math.PI/2, new Point(100, 100));

//        g2D.drawImage(spriteAnim.getSprite().image, 100, 100, 64, 64, null);
        spriteAnim.update(1);

        for (Point2D point: path.getSplinePoints()) {
            g2D.draw(new Rectangle2D.Double(point.getX(), point.getY(), 1, 1));
        }
        for (Point2D point: path.getSplineControls()) {
            g2D.draw(new Rectangle2D.Double(point.getX()-5, point.getY()-5, 10, 10));
        }
    }

    private void drawSprite(Graphics2D g2D, Sprite sprite, double rotation, Point coordinate) {
        AffineTransform reset = g2D.getTransform();
        g2D.rotate(rotation, coordinate.getX() + sprite.width / 2.0, coordinate.getY() + sprite.height / 2.0);
        g2D.drawImage(sprite.image, coordinate.x, coordinate.y, sprite.width, sprite.height, null);
        g2D.setTransform(reset);
    }
}