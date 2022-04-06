package towerDefence.view;

import towerDefence.enemies.IEnemy;
import towerDefence.level.path.PathPoint;
import towerDefence.view.sprite.Sprite;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;


public class BoardCanvas implements ICanvas{

    GameRenderable gameModel;

    SpriteEngine testSprite = new SpriteEngine("TestSpriteSheet.png", 4, 5, 10, 0);

//
//    Path2D test = new Path2D.Double();
//    List<Point2D> testPath = new ArrayList<>();

    public BoardCanvas(GameRenderable gameModel) {
        this.gameModel = gameModel;

        testSprite.start(0, 18, true);
    }

    @Override
    public void paint(Graphics2D g2D) {

        // Sprite engine test
        drawSprite(g2D, testSprite.getSprite(), new Point2D.Double(400, 100));
        testSprite.update(1);

        // TrackPath
        for (PathPoint point: gameModel.getTrackPath()) {
            g2D.draw(new Rectangle2D.Double(point.coordinate.getX(), point.coordinate.getY(), 1, 1));
        }

        // Spline control points
        for (Point2D point: gameModel.getSplineControls()) {
            g2D.draw(new Rectangle2D.Double(point.getX()-5, point.getY()-5, 10, 10));
        }

        // Enemies
        for (IEnemy enemy: gameModel.getEnemies()) {
            drawSprite(g2D, enemy.getSprite(), enemy.getPosition());
        }
    }

    private void drawSprite(Graphics2D g2D, Sprite sprite, Point2D coordinate) {
//        System.out.println(coordinate);

        AffineTransform reset = g2D.getTransform();

        g2D.rotate(sprite.getRotation(), coordinate.getX(), coordinate.getY());
        g2D.translate(coordinate.getX() - sprite.width / 2.0, coordinate.getY() - sprite.height / 2.0);
        g2D.drawImage(sprite.image, 0, 0, sprite.width, sprite.height, null);
        g2D.setTransform(reset);
    }
}