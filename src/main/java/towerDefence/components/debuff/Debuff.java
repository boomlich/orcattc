package towerDefence.components.debuff;

import towerDefence.enemies.IEnemy;
import towerDefence.view.sprite.SpriteEngine;

import java.util.Objects;

abstract class Debuff implements IDebuff {


    /**
     * Duration of the debuff in millisecounds
     */
    private final int duration;
    private int currentDuration;

    private boolean expired;

    private final String identifier;

    /**
     * The target with the debuff effect
     */
    private IEnemy target;

    /**
     * Sprite (animation) of the effect. E.g. a fire animation might
     * be applied to a damage over time fire effect.
     */
    private SpriteEngine spriteEngine;

    public Debuff(int duration, String identifier) {
        this.duration = duration;
        this.identifier = identifier;
        restartTimer();
    }

    protected void setSpriteEngine(SpriteEngine spriteEngine) {
        this.spriteEngine = spriteEngine;
    }

    protected SpriteEngine getSpriteEngine() {
        return spriteEngine;
    }

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

    protected IEnemy getTarget() {
        return target;
    }

    protected void restartTimer () {
        currentDuration = duration;
    }

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

    protected void restoreDuration() {
        System.out.println("Restored");
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

    @Override
    public int getComparisonValue() {
        return 0;
    }
}
