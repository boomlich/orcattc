package towerDefence.tower;

import towerDefence.components.Collision;
import towerDefence.components.Weapon;
import towerDefence.view.sprite.Sprite;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;

public class Tower implements ITower {

    private Point2D position;
    private int rank = 1;
    private Collision searchRadius;
    private final Collision placementRadius;
    private final Weapon weapon;
    private final SpriteEngine spriteBody;
    private SpriteEngine spriteBase;

    public Tower(Point2D position, Collision searchRadius, Collision placementRadius, Weapon weapon,
                 SpriteEngine spriteBody){
        this.position = position;
        this.searchRadius = searchRadius;
        this.placementRadius = placementRadius;
        this.weapon = weapon;
        this.spriteBody = spriteBody;

        spriteBase = new SpriteEngine("TestSpriteSheet.png", 4, 5, 10, 10);
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

    @Override
    public Sprite getBaseSprite() {
        return spriteBase.getSprite();
    }

    @Override
    public Sprite getBodySprite() {
        return spriteBody.getSprite();
    }

    @Override
    public Point2D getBodyPosition() {
        return position;
    }

    @Override
    public Point2D getBasePosition() {
        double offsetY = 10;

        return new Point2D.Double(position.getX(), position.getY() + offsetY);
    }


    @Override
    public int getZDepth() {
        return (int) position.getY();
    }

    @Override
    public Sprite getSprite() {
        return null;
    }
}
