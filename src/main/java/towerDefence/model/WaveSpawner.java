package towerDefence.model;

import towerDefence.level.EnemyWave;
import towerDefence.level.EnemyWithSpawnTime;

public class WaveSpawner {

    private EnemyWave currentWave;
    private final GameEntities gameEntities;
    private boolean spawningComplete;
    long delayTimer;

    public WaveSpawner(GameEntities gameEntities) {
        this.gameEntities = gameEntities;
    }

    protected void setCurrentWave(EnemyWave enemyWave) {
        this.currentWave = enemyWave;
        delayTimer = enemyWave.getStartSpawnTimer();
        spawningComplete = false;
    }

    protected void update(double deltaSteps) {
        if (!spawningComplete) {
            spawnEnemies(deltaSteps);
        }
    }

    private void spawnEnemies(double deltaSteps) {

        delayTimer -= deltaSteps;
        EnemyWithSpawnTime currentEnemy;

        if (currentWave.notEmpty()) {
            if (delayTimer <= 0) {
                currentEnemy = currentWave.getAndRemoveFirstEnemy();
                gameEntities.addEnemy(currentEnemy.getEnemy());
                delayTimer = currentEnemy.getTimer();
            }
        } else {
            spawningComplete = true;
        }
    }

    /**
     * @return true if all enemies in the current wave has been spawned
     */
    public boolean waveSpawnCompleted() {
        return spawningComplete;
    }
}
