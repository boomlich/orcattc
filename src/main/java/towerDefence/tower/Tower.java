package towerDefence.tower;

import towerDefence.components.*;
import towerDefence.enemies.IEnemy;
import towerDefence.model.GameEntities;
import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.Interaction.Interactable;
import towerDefence.view.sprite.Sprite;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Tower implements ITower, Interactable{

    private Point2D position;
    private int rank = 1;
    private Collision searchRadius;
    private final Collision placementRadius;
    private final Weapon weapon;
    private SpriteEngine spriteBody;
    private SpriteEngine spriteBase;
    private boolean spawnMode = true;
    private InteractCode interactCode = InteractCode.TARGET_A;
    private final int interactionSize = 40;
    private GameEntities gameEntities;
    private Targeting targeting;
    private IEnemy target;

    public Tower(Point2D position, Collision searchRadius, Collision placementRadius, Weapon weapon,
                 SpriteEngine spriteBody){
        this.position = position;
        this.searchRadius = searchRadius;
        this.placementRadius = placementRadius;
        this.weapon = weapon;
        this.spriteBody = spriteBody;
        spriteBase = new SpriteEngine("TestSpriteSheet.png", 4, 5, 10, 10);
        targeting = new Targeting(TargetingMode.CLOSEST, this);
    }

    @Override
    public void updateRank() {

    }

    @Override
    public void update(double deltaSteps) {

        spriteBody.update(deltaSteps);

        if (!spawnMode) {
            // Detect enemies
            List<IEnemy> detectedEnemies = searchRadius.updateCollision(gameEntities.getSortedEnemies());
            // Target an enemy
            if (detectedEnemies.size() > 0) {
                target = targeting.getTarget(detectedEnemies);
            } else if (target != null){
                target = null;
            }
        }
    }


    @Override
    public void disableSpawnMode() {
        spawnMode = false;
    }

    @Override
    public boolean activeSpawnMode(){
        return spawnMode;
    }

    @Override
    public void setGameEntities(GameEntities gameEntities) {
        this.gameEntities = gameEntities;
    }

    @Override
    public void setTargetingMode(TargetingMode targetingMode) {
        this.targeting = new Targeting(targetingMode, this);
    }

    @Override
    public IEnemy getTarget() {
        return target;
    }

    @Override
    public Collision getSearchRadius() {
        return searchRadius;
    }

    private void rank1() {

    }

    private void rank2() {

    }

    @Override
    public void updatePosition(Point2D newPosition) {
        position = calculateNewPosition(newPosition);
        searchRadius.setPosition(newPosition);
        placementRadius.setPosition(newPosition);
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

    private Point2D calculateNewPosition(Point2D newPosition) {
        double x = newPosition.getX() - spriteBody.getSprite().width / 2.0;
        double y = newPosition.getY() - spriteBody.getSprite().height / 2.0;

        return new Point2D.Double(x, y);
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

    @Override
    public int getX() {
        return (int) (position.getX());
    }


    @Override
    public int getY() {
        return (int) (position.getY());
    }

    @Override
    public int getWidth() {
        return interactionSize;
    }

    @Override
    public int getHeight() {
        return interactionSize;
    }

    @Override
    public String getTooltip() {
        return null;
    }

    @Override
    public void toggleHover() {
    }

    @Override
    public void toggleClick() {
        spriteBody = new SpriteEngine("TestSpriteSheet.png", 4, 5, 10, 15);
        spriteBody.start(new Animation(0, 11, false));
    }

    @Override
    public InteractCode getInteractCode() {
        return interactCode;
    }
}
