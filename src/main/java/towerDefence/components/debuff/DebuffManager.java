package towerDefence.components.debuff;

import towerDefence.enemies.IEnemy;

import java.util.ArrayList;
import java.util.List;

public class DebuffManager {

    private final List<DebuffDamageOverTime> damageOverTimeList = new ArrayList<>();
    private DebuffSlow slow;
    private final IEnemy ownerEnemy;

    public DebuffManager(IEnemy ownerEnemy) {
        this.ownerEnemy = ownerEnemy;
    }

    public void addDebuff(IDebuff debuff) {
        if (debuff.getClass() == DebuffSlow.class) {
            addSlowDebuff((DebuffSlow) debuff);
        } else if (debuff.getClass() == DebuffDamageOverTime.class) {
            addDamageOverTime((DebuffDamageOverTime) debuff);
        }
    }

    /**
     * @return list of all damage over time debuffs
     */
    public List<DebuffDamageOverTime> getDamageOverTimeList() {
        return damageOverTimeList;
    }

    public void update(double deltaSteps) {
        if (slow != null) {
            slow.update(deltaSteps);
            if (slow.isExpired()) {
                slow = null;
            }
        }

        // Update all damage over time and remove expired ones
        List<DebuffDamageOverTime> expiredDamages = new ArrayList<>();
        for (DebuffDamageOverTime DOT: damageOverTimeList) {
            DOT.update(deltaSteps);
            if (DOT.isExpired()) {
                expiredDamages.add(DOT);
            }
        }
        damageOverTimeList.removeAll(expiredDamages);
    }

    /**
     * Add a new debuff. If a slow debuff already applied, check if
     * it has the same identifier. Then restore the duration of the
     * effect already applied. If a slow effect with a different identifier
     * already applied, check if the slow effect on the new debuff is
     * greater, if so then replace. This is to prevent two slow effects
     * to be applied twice. Only a maximum of one slow effect should be
     * active at any time.
     *
     * @param debuff new slow effect
     */
    private void addSlowDebuff(DebuffSlow debuff) {
        if (slow != null) {
            if (slow.equals(debuff)) {
                slow.restoreDuration();
            } else if (replaceOldSlow(slow, debuff)) {
                slow.removeEffect();
                debuff.setTarget(ownerEnemy);
                slow = debuff;
            }
        } else {
            debuff.setTarget(ownerEnemy);
            slow = debuff;
        }
    }

    private boolean replaceOldSlow(DebuffSlow oldDebuff, DebuffSlow newDebuff) {
        return oldDebuff.getSlowMultiplier() <= newDebuff.getSlowMultiplier();
    }

    /**
     * Check if the same damage over time debuff already is applied, if so
     * then replenish the duration of the existing effect. Otherwise, add
     * the new effect. This is to prevent two of identical damage over time
     * effects to be applied at once.
     *
     * @param damageOverTime new damage over time debuff
     */
    private void addDamageOverTime(DebuffDamageOverTime damageOverTime) {
        damageOverTime.setTarget(ownerEnemy);
        boolean DOTAdded = false;
        for (int i = 0; i < damageOverTimeList.size(); i++) {
            if (damageOverTimeList.get(i).equals(damageOverTime)) {
                damageOverTimeList.set(i, damageOverTime);
                DOTAdded = true;
            }
        }
        if (!DOTAdded) {
            damageOverTimeList.add(damageOverTime);
        }

    }
}
