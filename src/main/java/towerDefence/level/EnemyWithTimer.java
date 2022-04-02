package towerDefence.level;

import towerDefence.enemies.IEnemy;

public class EnemyWithTimer {
    public final int timer;
    public final IEnemy enemy;

    public EnemyWithTimer(IEnemy enemy, int timer) {
        this.enemy = enemy;
        this.timer = timer;
    }
}
