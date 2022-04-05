package towerDefence.view;

import towerDefence.enemies.IEnemy;
import towerDefence.level.path.PathPoint;
import towerDefence.view.sprite.Sprite;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class BoardCanvas implements ICanvas{

    GameRenderable gameModel;

    public BoardCanvas(GameRenderable gameModel) {
        this.gameModel = gameModel;

    }

    @Override
    public void paint(Graphics2D g2D) {



        // Enemies
        for (IEnemy enemy: gameModel.getEnemies()) {
            drawSprite(g2D, enemy.getSprite(), enemy.getPosition());
        }

        // TrackPath
        for (PathPoint point: gameModel.getTrackPath()) {
            g2D.draw(new Rectangle2D.Double(point.coordinate.getX(), point.coordinate.getY(), 1, 1));
        }

        // Spline control points
        for (Point2D point: gameModel.getSplineControls()) {
            g2D.draw(new Rectangle2D.Double(point.getX()-5, point.getY()-5, 10, 10));
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