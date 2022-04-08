package towerDefence.tower;

import towerDefence.components.Collision;
import towerDefence.components.Weapon;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.*;
import java.awt.geom.Point2D;

public class Tower implements ITower{

    private Point2D position;
    private int rank;
    private Collision searchRadius;
    private final Collision placementRadius;
    private final Weapon weapon;
    private final SpriteEngine spriteEngine;

    public Tower(Collision searchRadius, Collision placementRadius, Weapon weapon, SpriteEngine spriteEngine){
        this.searchRadius = searchRadius;
        this.placementRadius = placementRadius;
        this.weapon = weapon;
        this.spriteEngine = spriteEngine;
    }

    @Override
    public void updateRank() {

    }

    @Override
    public void update(double deltaSteps) {

    }


    private void rank1() {

    }

    private void rank2() {

    }

    @Override
    public void updatePosition(Point2D newPosition) {
        position = newPosition;
    }


}
