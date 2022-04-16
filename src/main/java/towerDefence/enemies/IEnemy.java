package towerDefence.enemies;

import towerDefence.components.Collision;
import towerDefence.components.CollisionObject;
import towerDefence.components.damage.Damage;
import towerDefence.model.GameEntities;
import towerDefence.view.IRenderableObject;
import towerDefence.view.sprite.Sprite;

import java.awt.geom.Point2D;

public interface IEnemy extends IRenderableObject, CollisionObject {

    public void update(double deltaSteps);

    public Point2D getPosition();

    public void applyDamageOverTime(Damage damage);

    public Sprite getSprite();

    public boolean isDead();

    public Collision getCollision();

    public double getPathProgression();

    public double getHealth();
}
