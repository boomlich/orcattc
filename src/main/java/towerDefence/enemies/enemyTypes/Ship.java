package towerDefence.enemies.enemyTypes;

import towerDefence.components.movement.SplineMovement;
import towerDefence.enemies.Enemy;
import towerDefence.level.path.PathPoint;
import towerDefence.view.sprite.SpriteEngine;

import java.util.List;

public class Ship extends Enemy {

    public Ship () {

    }

    public Ship(List<PathPoint> path) {
        super(100,
                new SplineMovement(path, 0.5),
                new SpriteEngine("TestSpriteSheet.png", 4, 5, 15, 2));
    }
}
