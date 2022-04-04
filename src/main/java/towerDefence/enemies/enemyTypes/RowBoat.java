package towerDefence.enemies.enemyTypes;

import towerDefence.components.movement.LinearMovement;
import towerDefence.components.movement.SplineMovement;
import towerDefence.enemies.Enemy;

public class RowBoat extends Enemy {

    public RowBoat() {
    }

    public RowBoat(SplineMovement splineMovement) {
        super(100, splineMovement, null);
    }


}
