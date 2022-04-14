package towerDefence.view;

import towerDefence.enemies.IEnemy;
import towerDefence.level.path.PathPoint;
import towerDefence.tower.ITower;
import towerDefence.view.sprite.Sprite;
import towerDefence.view.sprite.SpriteEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.HashMap;
import java.util.List;


public class BoardCanvas implements ICanvas{

    private GameRenderable gameModel;
    private int width;
    private int height;

    public BoardCanvas(GameRenderable gameModel, int width, int height) {
        this.gameModel = gameModel;

        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(Graphics2D g2D) {

        g2D.setColor(new Color(38, 92, 66));
        g2D.fill(new Rectangle(0, 0, width, height));

        g2D.setColor(Color.black);

        // TrackPath
        for (PathPoint point: gameModel.getTrackPath()) {
            g2D.draw(new Rectangle2D.Double(point.coordinate.getX(), point.coordinate.getY(), 1, 1));
        }

        // Spline control points
        for (Point2D point: gameModel.getSplineControls()) {
            g2D.draw(new Rectangle2D.Double(point.getX()-5, point.getY()-5, 10, 10));
        }


        // Render out in z-depth order
        for (int i: gameModel.getZDepthRange()) {
            System.out.println(i);

            // Enemies


            HashMap<Integer, java.util.List<IEnemy>> enemies = gameModel.getEnemies();
            if (enemies.containsKey(i)) {
                for (IEnemy enemy: enemies.get(i)) {
                    drawSprite(g2D, enemy.getSprite(), enemy.getPosition());
                }
            }

            // Towers
            HashMap<Integer, List<ITower>> towers = gameModel.getTowers();
            if (towers.containsKey(i)) {
                for (ITower tower: towers.get(i)) {
                    drawSprite(g2D, tower.getBaseSprite(), tower.getBasePosition());
                    drawSprite(g2D, tower.getBodySprite(), tower.getBodyPosition());
                }
            }
        }
    }

    private void drawSprite(Graphics2D g2D, Sprite sprite, Point2D coordinate) {

        AffineTransform reset = g2D.getTransform();

        g2D.rotate(sprite.getRotation(), coordinate.getX(), coordinate.getY());
        g2D.translate(coordinate.getX() - sprite.width / 2.0, coordinate.getY() - sprite.height / 2.0);
        g2D.drawImage(sprite.image, 0, 0, sprite.width, sprite.height, null);
        g2D.setTransform(reset);
    }

}