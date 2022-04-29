package towerDefence.enemies;

import org.junit.jupiter.api.Test;
import towerDefence.components.damage.Damage;
import towerDefence.enemies.IEnemy;
import towerDefence.enemies.enemyTypes.OrcGrunt;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    void applyDamageTest() {
        IEnemy grunt = new OrcGrunt(null);
        int healthBefore = grunt.getHealth();

        // Damage without dying
        grunt.applyDamage(new Damage(55));
        assertEquals(healthBefore - 55, grunt.getHealth());
        assertFalse(grunt.isDead());

        // Test dying
        grunt.applyDamage(new Damage(55));
        assertTrue(grunt.isDead());
    }
}