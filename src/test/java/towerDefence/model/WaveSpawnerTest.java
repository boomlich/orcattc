package towerDefence.model;

import org.junit.jupiter.api.Test;
import towerDefence.enemies.IEnemy;
import towerDefence.enemies.enemyTypes.*;
import towerDefence.level.EnemyWave;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaveSpawnerTest {

    @Test
    void spawnWaveTest() {
        EnemyWave wave = new EnemyWave("D_15, a_2, db_1, P_60, abc_1");
        GameEntities entities = new GameEntities();
        WaveSpawner spawner = new WaveSpawner(entities);
        spawner.setCurrentWave(wave);

        IEnemy enemyType = new EmptyEnemy();

        int step = 0;
        while (wave.notEmpty()) {
            spawner.update(1.0);

            // Select enemy type per step
            if (step < 15) {
                enemyType = new RowBoat();
            } else if (step < 30) {
                enemyType = new RowBoat();
            } else if (step < 45) {
                enemyType = new BattleShip();
            } else if (step < 60) {
                enemyType = new Ship();
            } else if (step < 120) {
                enemyType = new EmptyEnemy();
            } else if (step < 135) {
                enemyType = new RowBoat();
            } else if (step < 150) {
                enemyType = new Ship();
            } else if (step < 165) {
                enemyType = new AttackShip();
            }

            IEnemy newestEnemy = entities.getEnemies().get(entities.getEnemies().size()-1);
            assertEquals(enemyType.getClass(), newestEnemy.getClass());

            step ++;
        }

    }
}
