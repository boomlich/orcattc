package towerDefence.enemies.enemyTypes;

import towerDefence.components.Animation;
import towerDefence.components.Collision;
import towerDefence.components.movement.SplineMovement;
import towerDefence.enemies.Enemy;
import towerDefence.level.path.SplinePathData;
import towerDefence.view.sprite.SpriteEngine;

public class OrcGrunt extends Enemy {

    public OrcGrunt() {
    }

    public OrcGrunt(SplinePathData path) {
        super(100,
                new SplineMovement(path, 1),
                new SpriteEngine("Sheet_OrcAxer.png", 2, 8, 10, 0),
                new Collision(22),
                new Animation(0, 8, true),
                new Animation(8, 16, true),
                new Animation(0, 0, false));
    }
}
