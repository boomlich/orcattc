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

    public void update(double deltaSteps);

    public Point2D getPosition();

    public Sprite getSprite();

    public boolean isDead();

    boolean hasReachedEnd();

    public Collision getCollision();

    public double getPathProgression();

    public int getHealth();

    public void applyDebuff(IDebuff debuff);

    public SplineMovement getMovement();

    DebuffManager getDebuffManager();

    int getMoneyLoot();

    int getReachedEndDamage();

    Point2D getDebuffPosition();
}
