package towerDefence.enemies;

import towerDefence.components.collision.Collision;
import towerDefence.components.collision.CollisionObject;
import towerDefence.components.damage.IDamageable;
import towerDefence.components.debuff.DebuffManager;
import towerDefence.components.debuff.IDebuff;
import towerDefence.components.movement.SplineMovement;
import towerDefence.view.IRenderableObject;
import towerDefence.view.sprite.Sprite;

import java.awt.geom.Point2D;

public interface IEnemy extends IRenderableObject, CollisionObject, IDamageable {

    /**
     * Update the enemy. Updates the enemy movement, progress, status
     * and sprite animation
     * @param deltaSteps change in time
     */
    void update(double deltaSteps);

    /**
     * Get position on the path. Offset sprite dimensions to match
     * the path position.
     *
     * @return position of the enemy
     */
    Point2D getPosition();

    /**
     * @return enemy sprite to be rendered
     */
    Sprite getSprite();

    /**
     * @return true if enemy is dead. Dies if health reaches 0
     * or enemy reaches end of path.
     */
    boolean isDead();

    /**
     * @return true if enemy has reached the end of the path.
     */
    boolean hasReachedEnd();

    /**
     * @return Collision circle of the enemy.
     */
    Collision getCollision();

    /**
     * @return current progression on the path in percentage. If halfway
     * through the path, it returns 0.5.
     */
    double getPathProgression();

    /**
     * @return current health value of the enemy. Determines if it is alive
     */
    int getHealth();

    /**
     * Send debuff to the debuffManager to added.
     *
     * @param debuff debuff to be applied
     */
    void applyDebuff(IDebuff debuff);

    /**
     * @return movement on the path of the enemy.
     */
    SplineMovement getMovement();

    /**
     * @return the debuffmanager that handles all debuffs.
     */
    DebuffManager getDebuffManager();

    /**
     * @return money that will be looted upon death.
     */
    int getMoneyLoot();

    /**
     * @return damage applied to the player if the enemy reaces the end
     */
    int getReachedEndDamage();
}
