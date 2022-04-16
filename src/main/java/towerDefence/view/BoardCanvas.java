package towerDefence.view;

import towerDefence.components.Collision;
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
        int p = 0;
        for (PathPoint point: gameModel.getTrackPath()) {
            g2D.draw(new Rectangle2D.Double(point.coordinate.getX(), point.coordinate.getY(), 1, 1));

        }

        // Spline control points
        for (Point2D point: gameModel.getSplineControls()) {
            g2D.draw(new Rectangle2D.Double(point.getX()-5, point.getY()-5, 10, 10));
        }

        // Draw path collision
        for (Collision collision: gameModel.getPathCollision()) {
            g2D.draw(new Ellipse2D.Double(collision.getPosition().getX(), collision.getPosition().getY(), 2 * collision.getRadius(), 2 * collision.getRadius()));
        }


        // Render out in z-depth order
        for (int i: gameModel.getZDepthRange()) {

            // Enemies
            HashMap<Integer, java.util.List<IEnemy>> enemies = gameModel.getEnemies();
            if (enemies.containsKey(i)) {
                for (IEnemy enemy: enemies.get(i)) {
                    DrawGraphics.drawSprite(g2D, enemy.getSprite(), enemy.getPosition());

                    double radius = enemy.getCollision().getRadius();
                    double collisionX = enemy.getCollision().getPosition().getX() - radius;
                    double collisionY = enemy.getCollision().getPosition().getY() - radius;


                    g2D.setColor(Color.BLUE);
                    g2D.draw(new Ellipse2D.Double(collisionX, collisionY, 2 * radius,  2 * radius));
                    g2D.draw(new Rectangle2D.Double(enemy.getCollision().getPosition().getX(),
                            enemy.getCollision().getPosition().getY(), 1, 1));
                }
            }

            // Towers
            HashMap<Integer, List<ITower>> towers = gameModel.getTowers();
            if (towers.containsKey(i)) {
                for (ITower tower: towers.get(i)) {
                    DrawGraphics.drawSprite(g2D, tower.getBaseSprite(), tower.getBasePosition());
                    DrawGraphics.drawSprite(g2D, tower.getBodySprite(), tower.getBodyPosition());



                    // Collision detection debug
                    if (tower.getTarget() != null) {
                        g2D.setColor(Color.RED);
                        g2D.draw(new Line2D.Double(
                                tower.getSearchRadius().getPosition().getX(),
                                tower.getSearchRadius().getPosition().getY(),
                                tower.getTarget().getCollision().getPosition().getX(),
                                tower.getTarget().getCollision().getPosition().getY()));
                    }
                }
            }
        }
    }
}