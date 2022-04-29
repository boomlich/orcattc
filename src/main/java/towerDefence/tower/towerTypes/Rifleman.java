package towerDefence.tower.towerTypes;

import towerDefence.components.collision.Collision;
import towerDefence.components.Weapons.ProjectileMultiShot;
import towerDefence.components.Weapons.Weapon;
import towerDefence.components.damage.Damage;
import towerDefence.tower.Cost;
import towerDefence.tower.Tower;
import towerDefence.tower.TowerUpgrades;
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
                new Weapon(1000, new ProjectileMultiShot(100, new Damage(100), 10, new Collision(5))),
                new SpriteEngine("graphics/tower/Sprite_Tower_RifleMan.png", 2, 9, 10, 4),
                Cost.RIFLEMAN);

        getWeapon().setTowerOwner(this);
    }

    @Override
    protected void rank1() {
        setUpgradeToolTip("Increase bullet penetration and damage");
        setSearchRadius(TowerUpgrades.upgradeDetectionRange(getSearchRadius(), 0.25));
    }

    @Override
    protected void rank2() {
        setUpgradeToolTip("Swap weapons. Fires 3 bullets simultaneously");
        getWeapon().increasePenetration(5);
        getWeapon().increaseDamage(1.5);
    }

    @Override
    protected void rank3() {
        getWeapon().increaseSpreadShots(3);
    }
}
