package towerDefence.tower.towerTypes;

import towerDefence.components.*;
import towerDefence.components.damage.Damage;
import towerDefence.components.debuff.DebuffDamageOverTime;
import towerDefence.particles.Particle;
import towerDefence.particles.ParticleEmitter;
import towerDefence.tower.Tower;
import towerDefence.tower.TowerUpgrades;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;

public class Archer extends Tower {

    SpriteEngine fireSprite = new SpriteEngine("graphics/FX/Sprite_FX_Fire_Small_01.png", 1, 4, 10, 0);
    Animation fireAnim = new Animation(0, 4, true);

    public Archer(Point2D position) {
        super(
                "Archer",
                "graphics/buttons/archer/Portrait_Bow_Hover.png",
                position,
                new Collision(100, false),
                new Collision(20, true),
                new Weapon(1000, new Projectile(100, new Damage(25), 10, new Collision(5))),
                new SpriteEngine("graphics/tower/Sprite_Tower_Archer.png", 2, 9, 10, 4));

        getWeapon().setTowerOwner(this);
        getWeapon().getProjectile().setDebuff(
                new DebuffDamageOverTime(
                        10000,
                        200,
                        30,
                        fireSprite,
                        fireAnim,
                        this,
                        "fireAR0"));
    }

    @Override
    protected void rank1() {
        setSearchRadius(TowerUpgrades.upgradeDetectionRange(getSearchRadius(), 0.30));
    }

    @Override
    protected void rank2() {
        getWeapon().getProjectile().setDebuff(new DebuffDamageOverTime(10000, 400, 30, fireSprite, fireAnim,this, "fireAR0"));
    }

    @Override
    protected void rank3() {
        getWeapon().getProjectile().setDamageRadius(new Collision(50, false));
    }
}