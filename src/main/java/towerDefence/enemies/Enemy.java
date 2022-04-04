package towerDefence.enemies;

import towerDefence.components.Damage;
import towerDefence.components.movement.SplineMovement;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;

public class Enemy implements IEnemy{

    int health;
    Damage damageOverTime;
    SplineMovement splineMovement;
    SpriteEngine spriteEngine;

    public Enemy(int health, SplineMovement splineMovement, SpriteEngine spriteEngine) {
        this.health = health;
        this.splineMovement = splineMovement;
        this.spriteEngine = spriteEngine;
    }

    // For testing
    public Enemy() {
    }

    @Override
    public void update(double deltaSteps) {
        splineMovement.update(deltaSteps);
    }

    @Override
    public Point2D getPosition() {
//        return splineMovement.getPosition();
        return null;
    }


}
