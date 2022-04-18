package towerDefence.tower.towerTypes;

import towerDefence.components.Collision;
import towerDefence.components.Projectile;
import towerDefence.components.Weapon;
import towerDefence.components.damage.Damage;
import towerDefence.tower.Tower;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;

public class Rifleman extends Tower {

    public Rifleman(Point2D position) {
        super(
                "Rifleman",
                "graphics/buttons/gun/Portrait_Gun_Hover.png",
                position,
                new Collision(100, false),
                new Collision(20, true),
                new Weapon(1000, new Projectile(100, new Damage(100), 10, new Collision(5))),
                new SpriteEngine("graphics/tower/Sprite_Tower_RifleMan.png", 2, 9, 10, 4));

        getWeapon().setTowerOwner(this);
    }
}
