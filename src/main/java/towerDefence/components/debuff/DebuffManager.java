package towerDefence.components.debuff;

import towerDefence.enemies.IEnemy;

import java.util.ArrayList;
import java.util.List;

public class DebuffManager {

    List<DebuffDamageOverTime> damageOverTimeList = new ArrayList<>();
    DebuffSlow slow;
    IEnemy ownerEnemy;

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
