package towerDefence.level;

import towerDefence.enemies.IEnemy;

public class EnemyWithSpawnTime {
    public final int timer;
    public final IEnemy enemy;

    public EnemyWithSpawnTime(IEnemy enemy, int timer) {
        this.enemy = enemy;
        this.timer = timer;
    }
}
