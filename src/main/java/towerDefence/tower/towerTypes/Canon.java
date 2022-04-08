package towerDefence.tower.towerTypes;

import towerDefence.components.Collision;
import towerDefence.components.Weapon;
import towerDefence.tower.Tower;
import towerDefence.view.sprite.SpriteEngine;

public class Canon extends Tower {
    public Canon(Collision searchRadius, Collision placementRadius, Weapon weapon, SpriteEngine spriteEngine) {
        super(searchRadius, placementRadius, weapon, spriteEngine);
    }
}
