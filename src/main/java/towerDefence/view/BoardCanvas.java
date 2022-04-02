package towerDefence.view;

import towerDefence.components.LinearMovement;
import towerDefence.enemies.IEnemy;
import towerDefence.enemies.enemyTypes.RowBoat;
import towerDefence.level.path.TrackPath;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BoardCanvas implements ICanvas{

    GameRenderable gameModel;

    Point2D pointA = new Point2D.Double(200, 200);
    Point2D pointB = new Point2D.Double(150, 100);

    IEnemy testEnemy = new RowBoat(new LinearMovement(1, pointA, pointB));


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

    public BoardCanvas(GameRenderable gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void paint(Graphics2D g2D) {

        

        g2D.fill(new Rectangle2D.Double(testEnemy.getPosition().getX(), testEnemy.getPosition().getY(), 50, 50));

        testEnemy.update(1);

        for (Point2D point: path.getSplinePoints()) {
            g2D.draw(new Rectangle2D.Double(point.getX(), point.getY(), 1, 1));
        }

        for (Point2D point: path.getSplineControls()) {
            g2D.draw(new Rectangle2D.Double(point.getX()-5, point.getY()-5, 10, 10));
        }
    }
}