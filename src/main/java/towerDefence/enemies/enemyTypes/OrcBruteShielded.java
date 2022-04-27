package towerDefence.enemies.enemyTypes;

import towerDefence.components.Animation;
import towerDefence.components.Collision;
import towerDefence.components.movement.SplineMovement;
import towerDefence.enemies.Enemy;
import towerDefence.level.path.SplinePathData;
import towerDefence.view.sprite.SpriteEngine;

public class OrcBruteShielded extends Enemy {

    public OrcBruteShielded() {

    }

    public OrcBruteShielded(SplinePathData path) {
        super(650,
                new SplineMovement(path, 0.65),
                new SpriteEngine("graphics/enemies/Sprite_Enemy_OrcBrute_Shielded.png", 2, 12, 10, 0),
                new Collision(20),
                new Animation(0, 12, true),
                new Animation(12, 24, true),
                new Animation(0, 0, true),
                20,
                5);
    }
}
