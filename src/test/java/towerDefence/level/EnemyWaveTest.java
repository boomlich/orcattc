package towerDefence.level;

import org.junit.jupiter.api.Test;
import towerDefence.enemies.IEnemy;
import towerDefence.enemies.enemyTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyWaveTest {

    @Test
    void parseWaveTest() {
        EnemyWave wave = new EnemyWave("D_15, a_2, db_1, P_60, abc_1", null);

        List<Integer> expectedDelayTimer = new ArrayList<>(Arrays.asList(15, 15, 15, 15, 60, 15, 15, 15));

        IEnemy typeA = new RowBoat();
        IEnemy typeB = new Ship();
        IEnemy typeC = new AttackShip();
        IEnemy typeD = new BattleShip();
        List<IEnemy> expectedEnemies = new ArrayList<>(Arrays.asList(typeA, typeA, typeD, typeB, typeA, typeB, typeC));

        for (int i = 0; i < expectedDelayTimer.size(); i++) {
            assertEquals(expectedDelayTimer.get(i), wave.getEnemies().get(i).timer);
            assertEquals(expectedEnemies.get(i).getClass(), wave.getEnemies().get(i).enemy.getClass());
        }
    }

    @Test
    void parseEnemyTest() {
        EnemyWave wave = new EnemyWave();

        IEnemy testEnemy = new AttackShip();
        assertEquals(testEnemy.getClass(), wave.parseEnemy('c').getClass());

        testEnemy = new RowBoat();
        assertEquals(testEnemy.getClass(), wave.parseEnemy('a').getClass());
    }
}
