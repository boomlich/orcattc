package towerDefence.tower.towerTypes;

import towerDefence.Math.MathHelperMethods;
import towerDefence.components.Collision;
import towerDefence.components.Projectile;
import towerDefence.components.ProjectileMultiShot;
import towerDefence.components.Weapon;
import towerDefence.components.damage.Damage;
import towerDefence.components.debuff.DebuffDamageOverTime;
import towerDefence.components.debuff.DebuffSlow;
import towerDefence.tower.Tower;
import towerDefence.tower.TowerUpgrades;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;

public class Wizard extends Tower {

    public Wizard(Point2D position) {
        super(
                "Wizard",
                "graphics/buttons/gun/Portrait_Gun_Hover.png",
                position,
                new Collision(100, false),
                new Collision(20, true),
                new Weapon(100, new Projectile(100, new Damage(25), 10, new Collision(5))),
                new SpriteEngine("graphics/tower/Sprite_Tower_Wizard.png", 1, 2, 10, 0));

        getWeapon().setTowerOwner(this);
    }

    @Override
    protected void rank1() {
        setSearchRadius(TowerUpgrades.upgradeDetectionRange(getSearchRadius(), 0.5));
    }

    @Override
    protected void rank2() {
        getWeapon().getProjectile().setDebuff(new DebuffSlow(5000, 0.5, 0.25, null, "freezeWR2"));
    }

    @Override
    protected void rank3() {
    }

    @Override
    protected void updateTargetDirection(Point2D target) {
        setBodyFrame(1);
    }

    @Override
    protected void noTarget() {
        super.noTarget();
        setBodyFrame(0);
    }
}
