package towerDefence.components.debuff;

import towerDefence.view.sprite.Animation;
import towerDefence.enemies.IEnemy;
import towerDefence.view.sprite.SpriteEngine;

import java.util.Objects;

abstract class Debuff implements IDebuff {

    /**
     * Duration of the debuff in millisecounds.
     */
    private final int duration;
    private int currentDuration;

    /**
     * Expired debuffs will be removed from the target.
     */
    private boolean expired;

    /**
     * Unique identifier of the debuff. Used for some debuff types
     * to determine if the debuff should be replenished or simply added
     * if a similar debuff is already applied.
     */
    private final String identifier;

    /**
     * The target with the debuff effect applied.
     */
    private IEnemy target;

    /**
     * Sprite (animation) of the effect. E.g. a fire animation might
     * be applied to a damage over time fire effect.
     */
    private SpriteEngine spriteEngine;
    private Animation spriteAnimation;

    public Debuff(int duration, String identifier) {
        this.duration = duration;
        this.identifier = identifier;
        restartTimer();
    }

    protected void setSpriteEngine(SpriteEngine spriteEngine, Animation spriteAnimation) {
        if (spriteEngine != null) {
            this.spriteEngine = spriteEngine;
            if (spriteAnimation != null) {
                this.spriteAnimation = spriteAnimation;
                this.spriteEngine.start(this.spriteAnimation);
            }
        }
    }

    protected Animation getSpriteAnimation() {
        return spriteAnimation;
    }

    public SpriteEngine getSpriteEngine() {
        return spriteEngine;
    }

    /**
     * @return unique string that identifies the debuff from other debuffs.
     */
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void update(double deltaSteps) {
        currentDuration -= 1000/60 * deltaSteps;

        if (spriteEngine != null) {
            spriteEngine.update(deltaSteps);
        }

        if (currentDuration <= 0) {
            effectTrigger();
        }
    }

    @Override
    public void setTarget(IEnemy target) {
        this.target = target;
    }

    /**
     * @return enemy with the debuff effect
     */
    protected IEnemy getTarget() {
        return target;
    }

    /**
     * Set the timer to the original duration value
     */
    protected void restartTimer () {
        currentDuration = duration;
    }

    /**
     * Set the effect to expired to remove the debuff form the target
     */
    protected void removeEffect() {
        expired = true;
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    /**
     * When timer reaches zero, this effect is triggered.
     */
    protected void effectTrigger() {

    }

    /**
     * Refresh duration of the entire debuff effect. If multiple ticks,
     * all ticks will be reset.
     */
    protected void restoreDuration() {
        restartTimer();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Debuff debuff = (Debuff) o;
        return Objects.equals(identifier, debuff.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public IDebuff makeCopy() {
        return null;
    }
}
