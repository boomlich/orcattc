package towerDefence.enemies.enemyTypes;

import towerDefence.view.sprite.Animation;
import towerDefence.components.collision.Collision;
import towerDefence.components.movement.SplineMovement;
import towerDefence.enemies.Enemy;
import towerDefence.level.path.SplinePathData;
import towerDefence.view.sprite.SpriteEngine;

public class OrcGruntBerserker extends Enemy {

    public OrcGruntBerserker() {
    }

    public OrcGruntBerserker(SplinePathData path) {
        super(200,
                new SplineMovement(path, 1.5),
                new SpriteEngine("graphics/enemies/Sprite_Enemy_OrcGruntBerserker.png", 2, 8, 10, 0),
                new Collision(14),
                new Animation(0, 8, true),
                new Animation(8, 16, true),
                new Animation(0, 0, false),
                15,
                4);
    }
}
