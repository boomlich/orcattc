package towerDefence.enemies.enemyTypes;

import towerDefence.components.Animation;
import towerDefence.components.Collision;
import towerDefence.components.movement.SplineMovement;
import towerDefence.enemies.Enemy;
import towerDefence.level.path.SplinePathData;
import towerDefence.view.sprite.SpriteEngine;

public class OrcBrute extends Enemy {

    public OrcBrute() {

    }

    public OrcBrute(SplinePathData path) {
        super(100,
                new SplineMovement(path, 0.5),
                new SpriteEngine("TestSpriteSheet.png", 4, 5, 15, 2),
                new Collision(10),
                new Animation(0, 8, true),
                new Animation(0, 0, true),
                new Animation(0, 0, true));
    }
}
