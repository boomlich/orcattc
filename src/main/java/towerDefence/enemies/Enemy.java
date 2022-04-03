package towerDefence.enemies;

import towerDefence.components.Damage;
import towerDefence.components.LinearMovement;
import towerDefence.view.sprite.Sprite;

import java.awt.geom.Point2D;

public class Enemy implements IEnemy{

    int health;
    Damage damageOverTime;
    LinearMovement linearMovement;
    Sprite sprite;

    public Enemy(int health, LinearMovement linearMovement, Sprite sprite) {
        this.health = health;
        this.linearMovement = linearMovement;
        this.sprite = sprite;
    }

    // For testing
    public Enemy() {
    }

    @Override
    public void update(double deltaSteps) {
        linearMovement.update(deltaSteps);
    }

    @Override
    public Point2D getPosition() {
        return linearMovement.getPosition();
    }


}
