package towerDefence.tower;

import towerDefence.components.collision.Collision;

public class TowerUpgrades {

    public static Collision upgradeDetectionRange(Collision range, double delta) {
        Collision updatedCollision = new Collision(range.getRadius() * (1.0 + delta), range.isSingleTarget());
        updatedCollision.setPosition(range.getPosition());
        return updatedCollision;
    }
}
