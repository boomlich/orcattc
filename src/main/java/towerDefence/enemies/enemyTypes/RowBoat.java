package towerDefence.enemies.enemyTypes;

import towerDefence.components.LinearMovement;
import towerDefence.enemies.Enemy;
import towerDefence.view.sprites.Sprite;

public class RowBoat extends Enemy {

    public RowBoat() {
    }

    public RowBoat(LinearMovement linearMovement) {
        super(100, linearMovement, new Sprite());
    }


}
