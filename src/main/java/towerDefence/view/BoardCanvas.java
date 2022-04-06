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
//
//    Path2D test = new Path2D.Double();
//    List<Point2D> testPath = new ArrayList<>();

    public BoardCanvas(GameRenderable gameModel) {
        this.gameModel = gameModel;

        testSprite.start(0, 18, true);

//        test.moveTo(128, 96);
//        test.quadTo(280,48, 240, 200);
//        test.quadTo(440,240, 424, 336);
//        PathIterator pi = test.getPathIterator(null, 0.5);
//        while (!pi.isDone()) {
//            double[] coords = new double[6];
//            switch (pi.currentSegment(coords)) {
//                case PathIterator.SEG_MOVETO:
//                case PathIterator.SEG_LINETO:
//                    testPath.add(new Point2D.Double(coords[0], coords[1]));
//                    break;
//            }
//            pi.next();
//        }


//        test.getPathIterator()

    }

    @Override
    public void paint(Graphics2D g2D) {


//        g2D.draw(test);

//        g2D.draw(new Rectangle2D.Double(128, 96, 10, 10));
//        g2D.draw(new Rectangle2D.Double(280, 48, 10, 10));
//        g2D.draw(new Rectangle2D.Double(240, 200, 10, 10));
//        g2D.draw(new Rectangle2D.Double(440, 240, 10, 10));
//        g2D.draw(new Rectangle2D.Double(424, 336, 10, 10));
//
//        for (Point2D point: testPath) {
//            g2D.draw(new Rectangle2D.Double(point.getX(), point.getY(), 1, 1));
//        }


        g2D.setColor(Color.RED);
        for (Point2D equalPoint: gameModel.getEqualPathPoints()) {
            g2D.fill(new Rectangle2D.Double(equalPoint.getX(), equalPoint.getY(), 5, 5));
        }

        g2D.setColor(Color.BLACK);


        // Sprite engine test
        drawSprite(g2D, testSprite.getSprite(), new Point2D.Double(400, 100));
        testSprite.update(1);


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