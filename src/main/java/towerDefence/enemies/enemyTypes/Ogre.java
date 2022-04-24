package towerDefence.enemies.enemyTypes;

import towerDefence.components.Animation;
import towerDefence.components.Collision;
import towerDefence.components.movement.SplineMovement;
import towerDefence.enemies.Enemy;
import towerDefence.level.path.SplinePathData;
import towerDefence.view.sprite.SpriteEngine;

public class Ogre extends Enemy {

    public Ogre() {

    }

    public Ogre(SplinePathData path) {
        super(10000,
                new SplineMovement(path, 0.25),
                new SpriteEngine("graphics/enemies/Sprite_Enemy_Ogre.png", 2, 9, 5, 0),
                new Collision(25),
                new Animation(0, 8, true),
                new Animation(9, 17, true),
                new Animation(0, 0, true));
    }
}
