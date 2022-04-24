package towerDefence.components.debuff;

import towerDefence.components.Animation;
import towerDefence.components.damage.Damage;
import towerDefence.tower.ITower;
import towerDefence.view.sprite.SpriteEngine;

public class DebuffDamageOverTime extends Debuff{

    /**
     * Total damage applied over the duration of the debuff.
     */
    private final int totalDamage;

    /**
     * Total duration of the damge over time effect. Damage
     * is distributed evenly over the total amount of ticks over
     * the total duration.
     */
    private final int totalDuration;

    /**
     * Total damage / total ticks.
     */
    private final Damage damagePerTick;
    private int currentTicks;

    /**
     * Total amount of ticks the damage is distributed over.
     * If 100 damage over 10 ticks = 10 damge per tick.
     */
    private final int totalTicks;

    /**
     * Origins of the debuff source. The tower that applied the debuff.
     */
    private final ITower debuffSource;

    public DebuffDamageOverTime(int duration, int totalDamage, int totalTicks, SpriteEngine spriteEngine, Animation spriteAnimation, ITower debuffSource, String identifier) {

        super(duration / totalTicks, identifier);
        this.totalDuration = duration;
        this.totalDamage = totalDamage;
        this.totalTicks = totalTicks;
        this.debuffSource = debuffSource;
        damagePerTick = new Damage(totalDamage / totalTicks);
        setSpriteEngine(spriteEngine, spriteAnimation);
    }

    @Override
    protected void effectTrigger() {
        int healthBefore = getTarget().getHealth();

        getTarget().applyDamage(damagePerTick);

        // Apply damage and kills stats
        if (debuffSource != null) {
            debuffSource.addStats(getTarget(), healthBefore, damagePerTick);
        }

        // Restart timer until reached the total amount of ticks
        if (currentTicks ++ <= totalTicks) {
            restartTimer();
        } else {
            removeEffect();
        }
    }

    @Override
    public IDebuff makeCopy() {
        return new DebuffDamageOverTime(totalDuration, totalDamage, totalTicks, getSpriteEngine(), getSpriteAnimation(), debuffSource, getIdentifier());
    }

    @Override
    protected void restoreDuration() {
        super.restoreDuration();
        currentTicks = 0;
    }
}
