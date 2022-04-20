package towerDefence.tower;

import towerDefence.components.Collision;
import towerDefence.components.damage.Damage;
import towerDefence.components.Projectile;
import towerDefence.components.Weapon;

public class TowerUpgrades {

    public static Collision upgradeDetectionRange(Collision range, double delta) {
        Collision updatedCollision = new Collision(range.getRadius() * (1.0 + delta), range.isSingleTarget());
        updatedCollision.setPosition(range.getPosition());
        return updatedCollision;
    }

    public static Weapon upgradeFireRate(Weapon weapon, double percentage) {
        return null;
    }

//    public static Projectile upgradeProjectilePenetration(Projectile projectile, int penetrationDelta) {
//        int health = projectile.getHealth() + projectile.getPenetrationDamage().getDamageValue() * penetrationDelta;
//        Projectile newProjectile = projectile.makeCopy();
//        newProjectile.setHealth(health);
//
//        return newProjectile;
//    }

    public static Collision upgradeImpactRadius() {
        return null;
    }

//    public static Projectile upgradeProjectileDamage(Projectile projectile, double percentageDelta) {
//        int damage = (int) (projectile.getDamage().getDamageValue() * (1.0 + percentageDelta));
//        Projectile newProjectile = projectile.makeCopy();
//        newProjectile.setDamage(damage);
//        return newProjectile;
//    }

}
