package towerDefence.level.components.debuff;

import org.junit.jupiter.api.Test;
import towerDefence.components.debuff.DebuffDamageOverTime;
import towerDefence.enemies.IEnemy;
import towerDefence.enemies.enemyTypes.OrcGrunt;
import towerDefence.tower.ITower;
import towerDefence.tower.towerTypes.Archer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DebuffTest {

    @Test
    void debuffManagerAndDamgeOverTimeTest() {
        IEnemy testEnemy = new OrcGrunt(null);
        ITower tower = new Archer();

        DebuffDamageOverTime damageOverTime = new DebuffDamageOverTime(1000, 100, 10, null, null, tower, "T");
        testEnemy.applyDebuff(damageOverTime);

        for (int i = 0; i < 10; i++) {
            assertEquals(100 - 10 * i, testEnemy.getHealth());
            testEnemy.getDebuffManager().update(7);
        }
        assertTrue(testEnemy.isDead());
    }
}
