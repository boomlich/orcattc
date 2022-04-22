package towerDefence.components.debuff;

import towerDefence.enemies.IEnemy;

public interface IDebuff {

    void update(double deltaSteps);

    void setTarget(IEnemy target);

    IDebuff makeCopy();

    boolean isExpired();

    String getIdentifier();

    int getComparisonValue();
}
