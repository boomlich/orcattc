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
    private final UI_HUD HUD;


    public UICanvas(GameRenderable gameModel, int width, int height) {
        this.gameModel = gameModel;
        UIContainer = new UIContainer(width, height);
        HUD = new UI_HUD(width, height, gameModel);
        UIContainer.add(HUD);
    }

    @Override
    public void paint(Graphics2D g2D) {
        UIContainer.paint(g2D);


        // Draw active/highlighted tower
        if (gameModel.hasActiveTower()) {
            ITower tower = gameModel.getActiveTower();

            double radius = tower.getSearchRadius().getRadius();
            double collisionX = tower.getSearchRadius().getPosition().getX() - radius;
            double collisionY = tower.getSearchRadius().getPosition().getY() - radius;

            if (tower.hasValidPlacement()){
                // Draw
                g2D.setColor(new Color(100, 100, 100, 100));
                g2D.fill(new Ellipse2D.Double(collisionX, collisionY, 2 * radius, 2 * radius));
                g2D.setColor(Color.WHITE);
                g2D.draw(new Ellipse2D.Double(collisionX, collisionY, 2 * radius, 2 * radius));
            } else {
                // Draw
                g2D.setColor(new Color(255, 0, 0, 40));
                g2D.fill(new Ellipse2D.Double(collisionX, collisionY, 2 * radius, 2 * radius));
                g2D.setColor(new Color(255, 0, 0, 150));
                g2D.draw(new Ellipse2D.Double(collisionX, collisionY, 2 * radius, 2 * radius));
            }


            DrawGraphics.drawSprite(g2D, tower.getBodySprite(), tower.getBodyPosition());

            // Center of Collision
            g2D.draw(new Rectangle2D.Double(tower.getSearchRadius().getPosition().getX(), tower.getSearchRadius().getPosition().getY(), 1, 1));
        }
    }

    @Override
    public void update(double deltaSteps) {
        if (gameModel.hasActiveTower()) {
            if (!gameModel.getActiveTower().activeSpawnMode()) {
                HUD.addTowerMenu(gameModel.getActiveTower());
            }
        } else {
            HUD.removeTowerMenu();
        }
    }

}
