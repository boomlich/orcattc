package towerDefence.tower;

import towerDefence.Math.MathHelperMethods;
import towerDefence.components.*;
import towerDefence.components.damage.Damage;
import towerDefence.enemies.IEnemy;
import towerDefence.model.GameEntities;
import towerDefence.view.Interaction.InteractCode;
import towerDefence.view.Interaction.Interactable;
import towerDefence.view.sprite.Sprite;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;
import java.util.List;

public abstract class Tower implements ITower, Interactable{

    private Point2D position;
    private int rank = 1;
    private Collision searchRadius;
    private final Collision placementRadius;
    private Weapon weapon;
    private SpriteEngine spriteBody;
    private SpriteEngine spriteBase;
    private boolean spawnMode = true;
    private InteractCode interactCode = InteractCode.TARGET_Tower;
    private final int interactionSize = 40;
    private GameEntities gameEntities;
    private Targeting targeting;
    private IEnemy target;
    private boolean validPlacement;
    private int cost;

    // Tower menu options
    private final String portraitPath;
    private final String towerName;
    private int totalKills = 0;
    private int totalDamageDone = 0;

    public Tower(String towerName, String portraitPath, Point2D position, Collision searchRadius, Collision placementRadius,
                 Weapon weapon, SpriteEngine spriteBody, int cost){
        this.towerName = towerName;
        this.portraitPath = portraitPath;
        this.position = position;
        this.searchRadius = searchRadius;
        this.placementRadius = placementRadius;
        this.weapon = weapon;
        this.spriteBody = spriteBody;
        spriteBase = new SpriteEngine("TestSpriteSheet.png", 4, 5, 10, 10);
        targeting = new Targeting(TargetingMode.FIRST, this);
        this.cost = cost;
    }

    @Override
    public void upgradeRank() {
        if (rank < 4) {
            switch (rank ++) {
                case 1 -> rank1();
                case 2 -> rank2();
                case 3 -> rank3();
            }
        }
    }

    protected void rank1() {

    }

    protected void rank2() {

    }

    protected void rank3() {
    }

    protected void setSearchRadius (Collision newRadius) {
        searchRadius = newRadius;
    }


    @Override
    public void update(double deltaSteps) {

        spriteBody.update(deltaSteps);

        if (!spawnMode) {
            // Detect enemies
            List<IEnemy> detectedEnemies = searchRadius.updateCollision(getEnemies());
            // Target and fire at enemy
            if (detectedEnemies.size() > 0) {
                target = targetEnemy(detectedEnemies, targeting);
                aimAndFireWeaponAtTarget(target, deltaSteps);
            } else if (target != null){
                noTarget();
            }
        } else {
            validPlacement = positionNotBlocked();
        }
    }

    protected void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    protected IEnemy targetEnemy(List<IEnemy> potentialTargets, Targeting targeting) {
        return targeting.getTarget(potentialTargets);
    }

    protected void aimAndFireWeaponAtTarget(IEnemy target, double deltaSteps) {
        updateTargetDirection(target.getCollision().getPosition());
        updateWeapon(deltaSteps, target.getCollision().getPosition());
    }

    protected List<IEnemy> getEnemies() {
        return gameEntities.getSortedEnemies();
    }

    protected void noTarget() {
        target = null;
    }

    protected void updateWeapon(double deltaSteps, Point2D target) {
        weapon.setProjectileSpawn(searchRadius.getPosition());
        weapon.setTarget(target);
        weapon.update(deltaSteps);
    }

    protected void updateTargetDirection(Point2D target) {

        int numberOfRotations = 16;
        double anglePerRotation = 2.0 * Math.PI / numberOfRotations;
        double firstFrameRange = -(Math.PI + anglePerRotation) / 2.0;

        Point2D vectorToTarget = MathHelperMethods.pointsToVector(searchRadius.getPosition(), target);
        double angleToTarget = MathHelperMethods.calculateVectorAngle(vectorToTarget);

        if (angleToTarget < Math.PI / 2.0 && angleToTarget > Math.PI / 2.0 - anglePerRotation / 2.0) {
            spriteBody.setFrame(8);
        } else if (angleToTarget < firstFrameRange) {
            spriteBody.setFrame(0);
        } else {
            spriteBody.setFrame(calculateFrameNumber(firstFrameRange, angleToTarget, anglePerRotation));
        }
    }

    private int calculateFrameNumber(double firstFrameRange, double angleToTarget, double anglePerRotation) {
        int frameNumber = (int) Math.ceil(Math.abs(firstFrameRange - angleToTarget) / anglePerRotation) - 1;

        if (angleToTarget > Math.PI) {
            frameNumber ++;
        }
        return frameNumber;
    }

    private boolean positionNotBlocked() {
        if (placementRadius.updateCollision(gameEntities.getBoardCollisions()).size() > 0) {
            return false;
        }
        return true;
    }

    protected void setBodyFrame(int frame) {
        spriteBody.setFrame(frame);
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
        weapon.setGameEntities(gameEntities);
    }

    protected GameEntities getGameEntities() {
        return gameEntities;
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
    public boolean hasValidPlacement() {
        return validPlacement;
    }

    @Override
    public String getPortraitPath() {
        return portraitPath;
    }

    @Override
    public String getName() {
        return towerName;
    }

    @Override
    public int getTotalKills() {
        return totalKills;
    }

    @Override
    public int getDamageDone() {
        return totalDamageDone;
    }

    @Override
    public void addKills(int deltaKills) {
        totalKills += deltaKills;
    }

    @Override
    public void addDamageDone(int deltaDamage) {
        totalDamageDone += deltaDamage;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void addStats(IEnemy enemy, int healthBefore, Damage damage) {
        if (enemy.isDead()) {
            addKills(1);
            addDamageDone(healthBefore);
        }
        else {
            addDamageDone(damage.getDamageValue());
        }
    }

    @Override
    public Collision getSearchRadius() {
        return searchRadius;
    }

    @Override
    public Collision getPlacementRadius() {
        return placementRadius;
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
    }

    @Override
    public InteractCode getInteractCode() {
        return interactCode;
    }

    @Override
    public void setInactive() {
        interactCode = InteractCode.INACTIVE;
    }

    @Override
    public int getCost() {
        return cost;
    }
}
