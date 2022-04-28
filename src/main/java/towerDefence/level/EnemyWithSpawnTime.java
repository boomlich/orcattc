package towerDefence.level;

import towerDefence.enemies.IEnemy;

public class EnemyWithSpawnTime {
    private int timer;
    protected final IEnemy enemy;

    public EnemyWithSpawnTime(IEnemy enemy, int timer) {
        this.enemy = enemy;
        this.timer = timer;
    }

    protected void addToTimer(int extraTime) {
        timer += extraTime;
//        timer = time;
    }

    public int getTimer() {
        return timer;
    }

    public IEnemy getEnemy() {
        return enemy;
    }
}
