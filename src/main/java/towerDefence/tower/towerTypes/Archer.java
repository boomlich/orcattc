package towerDefence.tower.towerTypes;

import towerDefence.components.Collision;
import towerDefence.components.Projectile;
import towerDefence.components.ProjectileMultiShot;
import towerDefence.components.Weapon;
import towerDefence.components.damage.Damage;
import towerDefence.tower.Tower;
import towerDefence.tower.TowerUpgrades;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;

public class Archer extends Tower {

    public Archer(Point2D position) {
        super(
                "Archer",
                "graphics/buttons/archer/Portrait_Bow_Hover.png",
                position,
                new Collision(100, false),
                new Collision(20, true),

                new Weapon(1000, new Projectile(100, new Damage(100), 10, new Collision(5))),

                new SpriteEngine("graphics/tower/Sprite_Tower_Archer.png", 2, 9, 10, 4));

        getWeapon().setTowerOwner(this);
    }

    @Override
    protected void rank1() {
        setSearchRadius(TowerUpgrades.upgradeDetectionRange(getSearchRadius(), 0.25));
    }

    @Override
    protected void rank2() {
        getWeapon().increasePenetration(5);
        getWeapon().increaseDamage(2);
    }

    @Override
    protected void rank3() {
        getWeapon().increaseSpreadShots(3);
    }
}