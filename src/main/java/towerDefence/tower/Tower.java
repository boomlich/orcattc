package towerDefence.tower;

import towerDefence.math.MathHelperMethods;
import towerDefence.components.collision.Collision;
import towerDefence.components.Targeting.Targeting;
import towerDefence.components.Targeting.TargetingMode;
import towerDefence.components.Weapons.Weapon;
import towerDefence.components.damage.Damage;
import towerDefence.enemies.IEnemy;
import towerDefence.model.GameEntities;
import UI.Interaction.InteractCode;
import UI.Interaction.Interactable;
import towerDefence.view.sprite.Sprite;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;
import java.util.List;

public abstract class Tower implements ITower, Interactable{

    private Point2D position;
    private int rank = 0;

    /**
     * Detection range. Enemies within this search radius are potential targets
     */
    private Collision searchRadius;

    /**
     * Detects obstacles on the map when spawning to assure that only places
     * on a valid position.
     */
    private final Collision placementRadius;

    private Weapon weapon;

    private SpriteEngine spriteBody;
    private boolean spawnMode = true;
    private InteractCode interactCode = InteractCode.TARGET_Tower;
    private final int interactionSize = 40;
    private GameEntities gameEntities;
    private Targeting targeting;
    private IEnemy target;
    private boolean validPlacement;
    private final Cost cost;

    // Tower menu options
    private final String portraitPath;
    private final String towerName;
    private int totalKills = 0;
    private int totalDamageDone = 0;
    private String upgradeToolTip = "Upgrade detection range";

    public Tower(String towerName, String portraitPath, Point2D position, Collision searchRadius, Collision placementRadius,
                 Weapon weapon, SpriteEngine spriteBody, Cost cost){
        this.towerName = towerName;
        this.portraitPath = portraitPath;
        this.position = position;
        this.searchRadius = searchRadius;
        searchRadius.setPosition(position);
        this.placementRadius = placementRadius;
        this.weapon = weapon;
        this.spriteBody = spriteBody;
        targeting = new Targeting(TargetingMode.FIRST, this);
        this.cost = cost;
    }

    // For testing
    public Tower() {
        this.searchRadius = new Collision(5);
        this.spriteBody = new SpriteEngine("TestSpriteSheet.png", 4, 5, 10, 0);
        this.placementRadius = new Collision(5);
        this.cost = null;
        this.portraitPath = null;
        this.towerName = null;
    }

    @Override
    public int upgradeRank() {
        if (rank < 3) {
            rank ++;
            switch (rank) {
                case 1 -> rank1();
                case 2 -> rank2();
                case 3 -> rank3();
            }
            return getCost(0);
        }
        return 0;
    }

    protected void setUpgradeToolTip(String upgradeToolTip) {
        this.upgradeToolTip = upgradeToolTip;
    }

    @Override
    public String getUpgradeToolTip() {
        return upgradeToolTip;
    }

    /**
     * Triggers when upgrading to rank 1
     */
    protected void rank1() {
    }

    /**
     * Trigger when upgrading to rank 2
     */
    protected void rank2() {
    }

    /**
     * Trigger when upgrading to rank 3
     */
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
        setBodyFrame(updateTargetDirection(target.getCollision().getPosition()));
        updateWeapon(deltaSteps, target.getCollision().getPosition());
    }

    protected List<IEnemy> getEnemies() {
        return gameEntities.getSortedEnemies(false);
    }

    protected void noTarget() {
        target = null;
    }

    protected void updateWeapon(double deltaSteps, Point2D target) {
        weapon.setProjectileSpawn(searchRadius.getPosition());
        weapon.setTarget(target);
        weapon.update(deltaSteps);
    }

    protected int updateTargetDirection(Point2D target) {

        int numberOfRotations = 16;
        double anglePerRotation = 2.0 * Math.PI / numberOfRotations;
        double firstFrameRange = -(Math.PI + anglePerRotation) / 2.0;

        Point2D vectorToTarget = MathHelperMethods.pointsToVector(searchRadius.getPosition(), target);
        double angleToTarget = MathHelperMethods.calculateVectorAngle(vectorToTarget);

        if (angleToTarget < Math.PI / 2.0 && angleToTarget > Math.PI / 2.0 - anglePerRotation / 2.0) {
            return 8;
        } else if (angleToTarget < firstFrameRange) {
            return 0;
        } else {
            return calculateFrameNumber(firstFrameRange, angleToTarget, anglePerRotation);
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
        return placementRadius.updateCollision(gameEntities.getBoardCollisions()).size() <= 0;
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

    public int getSellValue() {
        return cost.getSellValue(rank);
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
    public void setHover() {
    }

    @Override
    public void setNormal() {

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
    public int getCost(int rankOffset) {
        return cost.getCost(rank + rankOffset);
    }

    @Override
    public void disableInteraction() {
    }

    @Override
    public void enableInteraction() {

    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public TargetingMode getTargetingMode() {
        return targeting.targetingMode;
    }

    @Override
    public boolean isDead(){
        return false;
    }
}
