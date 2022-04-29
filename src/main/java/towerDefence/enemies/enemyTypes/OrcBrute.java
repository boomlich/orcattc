package towerDefence.enemies.enemyTypes;

import towerDefence.view.sprite.Animation;
import towerDefence.components.collision.Collision;
import towerDefence.components.movement.SplineMovement;
import towerDefence.enemies.Enemy;
import towerDefence.level.path.SplinePathData;
import towerDefence.view.sprite.SpriteEngine;

public class OrcBrute extends Enemy {

    public OrcBrute() {
    }

    public OrcBrute(int progression) {
        super(progression);
    }

    public OrcBrute(SplinePathData path) {
        super(300,
                new SplineMovement(path, 0.75),
                new SpriteEngine("graphics/enemies/Sprite_Enemy_OrcBrute.png", 2, 12, 10, 0),
                new Collision(20),
                new Animation(0, 12, true),
                new Animation(12, 24, true),
                new Animation(0, 0, true),
                10,
                3);
    }
}
