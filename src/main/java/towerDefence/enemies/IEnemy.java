package towerDefence.enemies;

import towerDefence.components.Collision;
import towerDefence.components.damage.Damage;
import towerDefence.model.GameEntities;
import towerDefence.view.sprite.Sprite;

import java.awt.geom.Point2D;

public interface IEnemy {

    public void update(double deltaSteps);

    public Point2D getPosition();

    public void applyDamageOverTime(Damage damage);

    public Sprite getSprite();

    public boolean isDead();

    public Collision getCollision();
}
