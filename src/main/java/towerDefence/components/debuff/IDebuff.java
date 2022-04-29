package towerDefence.components.debuff;

import towerDefence.enemies.IEnemy;

public interface IDebuff {

    /**
     * Update the timer and count down before fiering the effect
     * trigger.
     *
     * @param deltaSteps change in time
     */
    void update(double deltaSteps);

    /**
     * @param target enemy target that the debuff is applied on. Damage
     * over time, slow effect and other debuffs are applied to this target.
     */
    void setTarget(IEnemy target);

    /**
     * @return copy of the debuff with all it's parameters set.
     */
    IDebuff makeCopy();

    /**
     * @return true if debuff is done updating and should be removed
     */
    boolean isExpired();

    /**
     * @return a unique identifier to each debuff or type of debuff.
     */
    String getIdentifier();
}
