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
     * Upgrade the current rank of the tower.
     * @return
     */
    public int upgradeRank();

    public void update(double deltaSteps);

    public void updatePosition(Point2D newPosition);

    public Sprite getBodySprite();

    public Point2D getBodyPosition();

    public void disableSpawnMode();

    public Collision getSearchRadius();

    public Collision getPlacementRadius();

    public boolean activeSpawnMode();

    public void setGameEntities(GameEntities gameEntities);

    public void setTargetingMode(TargetingMode targetingMode);

    public IEnemy getTarget();

    public boolean hasValidPlacement();

    public String getPortraitPath();

    public String getName();

    String getUpgradeToolTip();

    public int getTotalKills();

    public int getDamageDone();

    public void addKills(int deltaKills);

    public void addDamageDone(int deltaDamage);

    public Weapon getWeapon();

    public void addStats(IEnemy enemy, int healthBefore, Damage damage);

    int getCost(int rankOffset);

    int getRank();

    int getSellValue();

    TargetingMode getTargetingMode();
}
