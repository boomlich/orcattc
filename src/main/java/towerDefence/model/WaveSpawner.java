package towerDefence.model;

import towerDefence.map.EnemyWave;
import towerDefence.map.EnemyWithTimer;

public class WaveSpawner {

    private EnemyWave currentWave;
    private final GameEntities gameEntities;
    long delayTimer;

    public WaveSpawner(GameEntities gameEntities) {
        this.gameEntities = gameEntities;
    }

    protected void setCurrentWave(EnemyWave enemyWave) {
        this.currentWave = enemyWave;
        delayTimer = 0;
    }

    protected void update(long steps) {
        spawnEnemies(steps);
    }

    private void spawnEnemies(long steps) {
        EnemyWithTimer currentEnemy;

        if (!currentWave.isEmpty()) {
            if (steps >= delayTimer) {
                currentEnemy = currentWave.getAndRemoveFirstEnemy();
                gameEntities.addEnemy(currentEnemy.enemy);
                delayTimer = steps + currentEnemy.timer;
            }
        }
    }
}
