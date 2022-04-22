package towerDefence.tower.towerTypes;

import towerDefence.components.Collision;
import towerDefence.components.Projectile;
import towerDefence.components.Weapon;
import towerDefence.components.damage.Damage;
import towerDefence.controller.MouseController;
import towerDefence.tower.Tower;
import towerDefence.tower.TowerUpgrades;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;

public class Cannon extends Tower {

    private final MouseController mouseController;
    private boolean mouseAim = false;

    public Cannon(Point2D position, MouseController mouseController) {
        super(
                "Cannon",
                "graphics/buttons/archer/Portrait_Bow_Hover.png",
                position,
                new Collision(60, false),
                new Collision(20, true),
                new Weapon(1000, new Projectile(100, new Damage(100), 10, new Collision(5))),
                new SpriteEngine("graphics/tower/Sprite_Tower_Cannon.png", 2, 9, 10, 4));


        getWeapon().setTowerOwner(this);
        getWeapon().getProjectile().setDamageRadius(new Collision(25, false));
        this.mouseController = mouseController;
    }

    @Override
    protected void rank1() {
        setSearchRadius(TowerUpgrades.upgradeDetectionRange(getSearchRadius(), 0.5));
    }

    @Override
    protected void rank2() {
        getWeapon().increaseDamage(2);
        getWeapon().getProjectile().setDamageRadius(new Collision(40, false));
    }

    @Override
    protected void rank3() {
        mouseAim = true;
    }

    @Override
    public void update(double deltaSteps) {
        if (mouseAim) {
            if (!activeSpawnMode()) {
                updateTargetDirection(mouseController.getMouseCoordinate());
                updateWeapon(deltaSteps, mouseController.getMouseCoordinate());
            }
        } else {
            super.update(deltaSteps);
        }
    }
}