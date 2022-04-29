package towerDefence.enemies.enemyTypes;

import towerDefence.view.sprite.Animation;
import towerDefence.components.collision.Collision;
import towerDefence.components.movement.SplineMovement;
import towerDefence.enemies.Enemy;
import towerDefence.level.path.SplinePathData;
import towerDefence.view.sprite.SpriteEngine;

public class OrcGruntShielded extends Enemy {

    public OrcGruntShielded() {
    }

    public OrcGruntShielded(SplinePathData path) {
        super(250,
                new SplineMovement(path, 0.9),
                new SpriteEngine("graphics/enemies/Sprite_Enemy_OrcGrunt_Shielded.png", 2, 8, 10, 0),
                new Collision(12),
                new Animation(0, 8, true),
                new Animation(8, 16, true),
                new Animation(0, 0, false),
                10,
                3);
    }
}
