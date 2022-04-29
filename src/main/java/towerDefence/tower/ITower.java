package towerDefence.tower;

import towerDefence.components.collision.Collision;
import towerDefence.components.Targeting.TargetingMode;
import towerDefence.components.Weapons.Weapon;
import towerDefence.components.damage.Damage;
import towerDefence.enemies.IEnemy;
import towerDefence.model.GameEntities;
import towerDefence.view.IRenderableObject;
import towerDefence.view.sprite.Sprite;

import java.awt.geom.Point2D;

public interface ITower extends IRenderableObject {

    /**
     * Upgrade the current rank of the tower up to the maximum available
     * ranks. Trigger the rank1, rank2, or rank3 methods.
     * @return cost of the upgrade.
     */
    public int upgradeRank();

    /**
     * set the towers position
     * @param newPosition new position of the tower
     */
    void updatePosition(Point2D newPosition);

    /**
     * @return the sprite of the tower body
     */
    Sprite getBodySprite();

    /**
     * @return the body sprite position of the tower
     */
    Point2D getBodyPosition();

    /**
     * Set spawn mode to false.
     */
    void disableSpawnMode();

    /**
     * @return collision circle for detecting enemies. Enemies
     * within this collision is potential targets
     */
    Collision getSearchRadius();

    /**
     * @return collision circle for placement of the tower in spawn mode.
     * Used for detecting valid placement on the map
     */
    Collision getPlacementRadius();

    /**
     * @return true if tower has spawn mode enabled
     */
    boolean activeSpawnMode();

    /**
     * @param gameEntities game entitiy manager that stores every entitiy
     */
    void setGameEntities(GameEntities gameEntities);

    /**
     * Change the tower's targeting mode. Targeting mode determines
     * which enemy within the detection range will be selected and
     * fired at.
     *
     * @param targetingMode desired targeting mode
     */
    void setTargetingMode(TargetingMode targetingMode);

    /**
     * @return the tower's current target
     */
    IEnemy getTarget();

    /**
     * @return true if placement collision don't detect any collision
     */
    boolean hasValidPlacement();

    /**
     * @return path to the portrait graphics
     */
    String getPortraitPath();

    /**
     * @return the tower display name
     */
    String getName();

    /**
     * @return the tooltip text attached to the upgrade rank
     */
    String getUpgradeToolTip();

    /**
     * @return the total amount of kills the tower has done since spawned
     */
    int getTotalKills();

    /**
     * @return the total amount of damage done to enemies since spawned.
     */
    int getDamageDone();

    /**
     * Add the amount of new kills to the stored total kills.
     *
     * @param deltaKills amount of new kills
     */
    void addKills(int deltaKills);

    /**
     * Add the amount of damage done to the total amount of damage done.
     *
     * @param deltaDamage amount of new damage done
     */
    void addDamageDone(int deltaDamage);

    /**
     * @return active weapon of the tower
     */
    Weapon getWeapon();

    /**
     * Calculate the number of damage and check if the enemy is dead to
     * add the number of kills and damage done to the tower statistics.
     *
     * @param enemy enemy damaged
     * @param healthBefore enemy health prior to damage
     * @param damage damage value of the projectile
     */
    void addStats(IEnemy enemy, int healthBefore, Damage damage);

    /**
     * Calculate the cost of the interaction. Either purchasing new tower or upgrading.
     *
     * @param rankOffset 1 to check cost of next rank upgrade. 0 to get cost of current rank/purchase
     * @return cost of the tower/upgrade
     */
    int getCost(int rankOffset);

    /**
     * @return current rank of the tower
     */
    int getRank();

    /**
     * Calculate the amount of money returned to player if tower is sold. Based
     * of the total amount of money spent on the tower.
     *
     * @return money returned if sold.
     */
    int getSellValue();

    /**
     * @return currently active targeting mode
     */
    TargetingMode getTargetingMode();
}
