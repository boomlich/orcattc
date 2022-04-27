package towerDefence.enemies.enemyTypes;

import towerDefence.components.Animation;
import towerDefence.components.Collision;
import towerDefence.components.movement.SplineMovement;
import towerDefence.enemies.Enemy;
import towerDefence.level.path.SplinePathData;
import towerDefence.view.sprite.SpriteEngine;

public class OrcBruteBerserkerShielded extends Enemy {

    public OrcBruteBerserkerShielded() {

    }

    public OrcBruteBerserkerShielded(SplinePathData path) {
        super(900,
                new SplineMovement(path, 1),
                new SpriteEngine("graphics/enemies/Sprite_Enemy_OrcBruteBerserker_Shielded.png", 2, 12, 10, 0),
                new Collision(20),
                new Animation(0, 12, true),
                new Animation(12, 24, true),
                new Animation(0, 0, true),
                50,
                9);
    }
}
