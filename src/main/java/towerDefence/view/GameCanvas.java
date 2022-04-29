package towerDefence.view;

import towerDefence.components.collision.Collision;
import towerDefence.components.Weapons.Projectile;
import towerDefence.components.debuff.DebuffDamageOverTime;
import towerDefence.components.debuff.DebuffManager;
import towerDefence.enemies.IEnemy;
import towerDefence.components.particles.Particle;
import towerDefence.tower.ITower;
import towerDefence.view.sprite.Sprite;

import java.awt.*;
import java.awt.geom.*;
import java.util.HashMap;
import java.util.List;


public class GameCanvas {

    private final GameRenderable gameModel;
    private final int width;
    private final int height;

    public GameCanvas(GameRenderable gameModel, int width, int height) {
        this.gameModel = gameModel;

        this.width = width;
        this.height = height;
    }

    public void paint(Graphics2D g2D) {

        g2D.setColor(new Color(38, 92, 66));
        g2D.fill(new Rectangle(0, 0, width, height));

        g2D.drawImage(gameModel.getMapGraphics(), 0, 0, width, height, null);

        g2D.setColor(Color.black);

        // Draw path collision
        if (RenderingOptions.DEBUG){
            if (RenderingOptions.PATH_COLLISION) {
                for (Collision collision: gameModel.getPathCollision()) {
                    g2D.fill(new Ellipse2D.Double(
                            collision.getPosition().getX() - collision.getRadius(),
                            collision.getPosition().getY() - collision.getRadius(),
                            2 * collision.getRadius(),
                            2 * collision.getRadius()));
                }
            }
        }


        // Render out in z-depth order
        for (int i: gameModel.getZDepthRange()) {

            // Enemies
            HashMap<Integer, java.util.List<IEnemy>> enemies = gameModel.getEnemies();
            if (enemies.containsKey(i)) {
                for (IEnemy enemy: enemies.get(i)) {
                    DrawGraphics.drawSprite(g2D, enemy.getSprite(), enemy.getPosition());
                    drawDebuffs(g2D, enemy);

                    if (RenderingOptions.DEBUG) {
                        if (RenderingOptions.ENEMY_COLLISION) {
                            double radius = enemy.getCollision().getRadius();
                            double collisionX = enemy.getCollision().getPosition().getX() - radius;
                            double collisionY = enemy.getCollision().getPosition().getY() - radius;

                            g2D.setColor(Color.BLUE);
                            g2D.draw(new Ellipse2D.Double(collisionX, collisionY, 2 * radius,  2 * radius));
                        }
                        if (RenderingOptions.ENEMY_HEALTH) {
                            g2D.drawString(String.valueOf(enemy.getHealth()), (int) enemy.getPosition().getX(), (int) (enemy.getPosition().getY() - 10));
                        }
                    }
                }
            }

            // Towers
            HashMap<Integer, List<ITower>> towers = gameModel.getTowers();
            if (towers.containsKey(i)) {
                for (ITower tower: towers.get(i)) {
//                    DrawGraphics.drawSprite(g2D, tower.getBaseSprite(), tower.getBasePosition());
                    DrawGraphics.drawSprite(g2D, tower.getBodySprite(), tower.getBodyPosition());


                    if (RenderingOptions.DEBUG) {
                        double collisionX = tower.getSearchRadius().getPosition().getX();
                        double collisionY = tower.getSearchRadius().getPosition().getY();

                        if (RenderingOptions.TOWER_TARGETING) {
                            if (tower.getTarget() != null) {
                                g2D.setColor(Color.RED);
                                g2D.draw(new Line2D.Double(
                                        collisionX, collisionY,
                                        tower.getTarget().getCollision().getPosition().getX(),
                                        tower.getTarget().getCollision().getPosition().getY()));
                            }
                        }
                        if (RenderingOptions.TOWER_SEARCH_RADIUS) {
                            g2D.setColor(Color.BLUE);
                            double radius = tower.getSearchRadius().getRadius();
                            g2D.draw(new Ellipse2D.Double(collisionX - radius, collisionY - radius, 2 * radius, 2 * radius));
                        }
                        if (RenderingOptions.TOWER_PLACEMENT_RADIUS) {
                            double radius = tower.getPlacementRadius().getRadius();
                            g2D.draw(new Ellipse2D.Double(collisionX - radius, collisionY - radius, 2 * radius, 2 * radius));
                        }
                    }
                }
            }
        }

        for (Projectile projectile: gameModel.getProjectiles()) {
            g2D.setColor(Color.RED);
            g2D.fill(new Ellipse2D.Double(projectile.getPosition().getX()-5, projectile.getPosition().getY() - 5, 10, 10));
        }
        for (Particle particle: gameModel.getParticles()) {
            DrawGraphics.drawSprite(g2D, particle.getSprite(), particle.getPosition());
        }
    }

    private void drawDebuffs(Graphics2D g2D, IEnemy enemy) {
        for (DebuffDamageOverTime DOT: enemy.getDebuffManager().getDamageOverTimeList()) {
            if (DOT.getSpriteEngine() != null) {
                Sprite sprite = DOT.getSpriteEngine().getSprite();
                Point2D position = new Point2D.Double(
                        enemy.getCollision().getPosition().getX() - sprite.width / 2.0,
                        enemy.getCollision().getPosition().getY() - sprite.height / 2.0
                );
                DrawGraphics.drawSprite(g2D, sprite, position);
            }
        }
    }
}