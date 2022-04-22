package towerDefence.tower.towerTypes;

import towerDefence.components.*;
import towerDefence.components.damage.Damage;
import towerDefence.components.debuff.DebuffSlow;
import towerDefence.enemies.IEnemy;
import towerDefence.tower.Tower;
import towerDefence.tower.TowerUpgrades;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;

public class Wizard extends Tower {

    private final int ultimateCooldown = 5000;
    private Weapon ultimateWeapon;
    private final Weapon originalWeapon;
    private int currentCooldown = ultimateCooldown;



    public Wizard(Point2D position) {
        super(
                "Wizard",
                "graphics/buttons/gun/Portrait_Gun_Hover.png",
                position,
                new Collision(100, false),
                new Collision(20, true),
                new Weapon(100, new Projectile(100, new Damage(25), 10, new Collision(5))),
                new SpriteEngine("graphics/tower/Sprite_Tower_Wizard.png", 1, 2, 10, 0));

        originalWeapon = getWeapon();
        originalWeapon.setTowerOwner(this);
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
        ultimateWeapon = new Weapon(1, new Projectile(10000, new Damage(300), 50, new Collision(10)));
        ultimateWeapon.setTowerOwner(this);
        ultimateWeapon.setGameEntities(getGameEntities());
    }

    @Override
    public void update(double deltaSteps) {
        if (ultimateWeapon != null) {
            fireUltimateWeapon(deltaSteps);
        }

        super.update(deltaSteps);
    }

    private void fireUltimateWeapon(double deltaSteps) {
        currentCooldown -= 1000/60 * deltaSteps;
        if (currentCooldown <= 0) {
            IEnemy target = new Targeting(TargetingMode.FIRST, this).getTarget(getEnemies());
            setWeapon(ultimateWeapon);
            aimAndFireWeaponAtTarget(target, deltaSteps);
            currentCooldown = ultimateCooldown;
            setWeapon(originalWeapon);
        }
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
