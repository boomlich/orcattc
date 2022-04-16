package towerDefence.tower.towerTypes;

import towerDefence.components.Collision;
import towerDefence.components.Weapon;
import towerDefence.tower.Tower;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;

public class Rifleman extends Tower {

    public Rifleman(Point2D position) {
        super(position,
                new Collision(100, false),
                new Collision(20, true),
                new Weapon(),
                new SpriteEngine("TestSpriteSheet.png", 4, 5, 10, 5));
    }
}
