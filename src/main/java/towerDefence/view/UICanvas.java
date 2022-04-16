package towerDefence.view;

import towerDefence.tower.ITower;
import towerDefence.view.UI.components.*;
import towerDefence.view.UI.presets.UI_HUD;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class UICanvas implements ICanvas {

    private final GameRenderable gameModel;
    private final UIContainer UIContainer;


    public UICanvas(GameRenderable gameModel, int width, int height) {
        this.gameModel = gameModel;
        UIContainer = new UIContainer(width, height);
        UIContainer.add(new UI_HUD(width, height));
    }

    @Override
    public void paint(Graphics2D g2D) {
        UIContainer.paint(g2D);


        // Draw active/highlighted tower
        if (gameModel.hasActiveTower()) {
            ITower tower = gameModel.getActiveTower();
            int x = (int) tower.getBodyPosition().getX();
            int y = (int) tower.getBodyPosition().getY();
            double radius = tower.getSearchRadius().getRadius();

            double collisionX = tower.getSearchRadius().getPosition().getX() - radius;
            double collisionY = tower.getSearchRadius().getPosition().getY() - radius;


            // Draw
            g2D.setColor(new Color(100, 100, 100, 100));
            g2D.fill(new Ellipse2D.Double(collisionX, collisionY, 2 * radius, 2 * radius));
            g2D.setColor(Color.WHITE);
            g2D.draw(new Ellipse2D.Double(collisionX, collisionY, 2 * radius, 2 * radius));

            DrawGraphics.drawSprite(g2D, tower.getBodySprite(), tower.getBodyPosition());

            // Center of Collision
            g2D.draw(new Rectangle2D.Double(tower.getSearchRadius().getPosition().getX(), tower.getSearchRadius().getPosition().getY(), 1, 1));
        }
    }

}
