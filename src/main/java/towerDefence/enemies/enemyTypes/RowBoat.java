package towerDefence.enemies.enemyTypes;

import towerDefence.components.movement.LinearMovement;
import towerDefence.components.movement.SplineMovement;
import towerDefence.enemies.Enemy;
import towerDefence.level.path.PathPoint;
import towerDefence.level.path.SplinePathData;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;
import java.util.List;

public class RowBoat extends Enemy {

    public RowBoat() {
    }

    public RowBoat(SplinePathData path) {
        super(100,
                new SplineMovement(path, 1),
                new SpriteEngine("TestSpriteSheet.png", 4, 5, 15, 1));
    }
}
