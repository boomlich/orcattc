package towerDefence.tower;

import towerDefence.components.Collision;
import towerDefence.components.TargetingMode;
import towerDefence.enemies.IEnemy;
import towerDefence.model.GameEntities;
import towerDefence.view.IRenderableObject;
import towerDefence.view.sprite.Sprite;

import java.awt.geom.Point2D;

public interface ITower extends IRenderableObject {

    /**
     * Upgrade the current rank of the tower.
     */
    public void updateRank();

    public void update(double deltaSteps);

    public void updatePosition(Point2D newPosition);

    public Sprite getBaseSprite();

    public Sprite getBodySprite();

    public Point2D getBodyPosition();

    public Point2D getBasePosition();

    public void disableSpawnMode();

    public Collision getSearchRadius();

    public Collision getPlacementRadius();

    public boolean activeSpawnMode();

    public void setGameEntities(GameEntities gameEntities);

    public void setTargetingMode(TargetingMode targetingMode);

    public IEnemy getTarget();

    public boolean hasValidPlacement();

}
