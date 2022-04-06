package towerDefence.model;

import towerDefence.level.EnemyWave;
import towerDefence.level.EnemyWithSpawnTime;

public class WaveSpawner {

    private EnemyWave currentWave;
    private final GameEntities gameEntities;
    long delayTimer;

    public WaveSpawner(GameEntities gameEntities) {
        this.gameEntities = gameEntities;
    }

    protected void setCurrentWave(EnemyWave enemyWave) {
        this.currentWave = enemyWave;
        delayTimer = enemyWave.getStartSpawnTimer();
    }

    protected void update(double deltaSteps) {
        spawnEnemies(deltaSteps);
    }

    private void spawnEnemies(double deltaSteps) {

        delayTimer -= deltaSteps;
        EnemyWithSpawnTime currentEnemy;

        if (currentWave.notEmpty()) {
            if (delayTimer <= 0) {
                currentEnemy = currentWave.getAndRemoveFirstEnemy();
                gameEntities.addEnemy(currentEnemy.enemy);
                delayTimer = currentEnemy.timer;
            }
        }
    }
}
