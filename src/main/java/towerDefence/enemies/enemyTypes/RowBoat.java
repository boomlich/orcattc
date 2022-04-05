package towerDefence.enemies.enemyTypes;

import towerDefence.components.movement.LinearMovement;
import towerDefence.components.movement.SplineMovement;
import towerDefence.enemies.Enemy;
import towerDefence.level.path.PathPoint;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;
import java.util.List;

public class RowBoat extends Enemy {

    public RowBoat() {
    }

    public RowBoat(List<PathPoint> path) {
        super(100,
                new SplineMovement(path, 0.5),
                new SpriteEngine("TestSpriteSheet.png", 4, 5, 15, 1));
    }
}
