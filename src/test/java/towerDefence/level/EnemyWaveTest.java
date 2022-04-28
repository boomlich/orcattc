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
        EnemyWave wave = new EnemyWave("P_20, D_15, a_2, db_1, P_60, abc_1, D_30, defghi_1", null);

        IEnemy typeA = new OrcGrunt();
        IEnemy typeB = new OrcGruntShielded();
        IEnemy typeC = new OrcBrute();
        IEnemy typeD = new OrcBruteShielded();
        IEnemy typeE = new OrcGruntBerserker();
        IEnemy typeF = new OrcGruntBerserkerShielded();
        IEnemy typeG = new OrcBruteBerserker();
        IEnemy typeH = new OrcBruteBerserkerShielded();
        IEnemy typeI = new Ogre();

        List<Integer> expectedDelayTimer = new ArrayList<>(Arrays.asList(15, 15, 15, 75, 15, 15, 15, 30, 30, 30, 30, 30, 30));
        List<IEnemy> expectedEnemies = new ArrayList<>(Arrays.asList(typeA, typeA, typeD, typeB, typeA, typeB, typeC, typeD, typeE, typeF, typeG, typeH, typeI));

        for (int i = 0; i < expectedDelayTimer.size(); i++) {
            assertEquals(expectedDelayTimer.get(i), wave.getEnemies().get(i).getTimer());
            assertEquals(expectedEnemies.get(i).getClass(), wave.getEnemies().get(i).enemy.getClass());
        }
    }
}
