package towerDefence.tower.towerTypes;

import towerDefence.components.Collision;
import towerDefence.components.Weapon;
import towerDefence.tower.Tower;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;

public class Canon extends Tower {

    public Canon(Point2D position, Collision searchRadius, Collision placementRadius, Weapon weapon, SpriteEngine spriteBody) {
        super(position, searchRadius, placementRadius, weapon, spriteBody);
    }
}
