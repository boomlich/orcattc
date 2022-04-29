package towerDefence.components.Targeting;

import towerDefence.math.MathHelperMethods;
import towerDefence.enemies.IEnemy;
import towerDefence.tower.ITower;

import java.util.List;

public class Targeting {

    private final ITower tower;
    public final TargetingMode targetingMode;

    public Targeting(TargetingMode targetingMode, ITower tower) {
        this.targetingMode = targetingMode;
        this.tower = tower;
    }

    public IEnemy getTarget(List<IEnemy> potentialTargets) {

        switch (targetingMode) {
            case FIRST -> {
                return targetFirst(potentialTargets);
            }
            case STRONGEST -> {
                return targetStrongest(potentialTargets);
            }
            case LAST -> {
                return targetLast(potentialTargets);
            }
            case CLOSEST -> {
                return targetClosest(potentialTargets);
            }
        }

        return null;
    }

    private IEnemy targetFirst(List<IEnemy> potentialTargets) {
        return potentialTargets.get(0);
    }

    private IEnemy targetLast(List<IEnemy> potentialTargets) {
        return potentialTargets.get(potentialTargets.size()-1);
    }

    private IEnemy targetStrongest(List<IEnemy> potentialTargets) {
        IEnemy strongest = null;
        double strongestHealth = 0;

        for (IEnemy enemy: potentialTargets) {
            if (enemy.getHealth() > strongestHealth) {
                strongest = enemy;
                strongestHealth = enemy.getHealth();
            }
        }
        return strongest;
    }

    private IEnemy targetClosest(List<IEnemy> potentialTargets) {
        IEnemy closest = null;
        double closestDistance = 100000;

        for (IEnemy enemy: potentialTargets) {
            double distance = MathHelperMethods.vectorLength(
                    enemy.getCollision().getPosition(),
                    tower.getSearchRadius().getPosition());

            if (distance < closestDistance) {
                closest = enemy;
                closestDistance = distance;
            }
        }
        return closest;
    }
}
