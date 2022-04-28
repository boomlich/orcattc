package towerDefence.model;

import org.junit.jupiter.api.Test;
import towerDefence.enemies.IEnemy;
import towerDefence.enemies.enemyTypes.*;
import towerDefence.level.EnemyWave;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaveSpawnerTest {

    @Test
    void spawnWaveTest() {
        EnemyWave wave = new EnemyWave("P_11, D_15, a_2, db_1, P_60, abc_1", null);
        GameEntities entities = new GameEntities();
        WaveSpawner spawner = new WaveSpawner(entities);
        spawner.setCurrentWave(wave);

        IEnemy enemyType = null;

        int step = 0;
        while (wave.notEmpty()) {
            spawner.update(1.0);

            if (step > 10) {
                // Select enemy type per step
                if (step < 25) {
                    enemyType = new OrcGrunt();
                } else if (step < 40) {
                    enemyType = new OrcGrunt();
                } else if (step < 55) {
                    enemyType = new OrcBruteShielded();
                } else if (step < 130) {
                    enemyType = new OrcGruntShielded();
                } else if (step < 145) {
                    enemyType = new OrcGrunt();
                } else if (step < 160) {
                    enemyType = new OrcGruntShielded();
                } else if (step < 175) {
                    enemyType = new OrcBrute();
                }

                IEnemy newestEnemy = entities.getSortedEnemies(false).get(entities.getSortedEnemies(false).size() - 1);
                assertEquals(enemyType.getClass(), newestEnemy.getClass());
            }
            step ++;
        }

    }
}
