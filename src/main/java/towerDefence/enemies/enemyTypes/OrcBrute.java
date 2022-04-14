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
                new SplineMovement(path, 0.75),
                new SpriteEngine("graphics/enemies/Sheet_Orc_Brute.png", 2, 12, 10, 0),
                new Collision(10),
                new Animation(0, 12, true),
                new Animation(12, 24, true),
                new Animation(0, 0, true));
    }
}
