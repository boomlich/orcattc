package towerDefence.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HealthManagerTest {

    @Test
    void reduceHealthTest() {
        HealthManager healthManager = new HealthManager(100);

        assertTrue(healthManager.reduceHealth(49));
        assertEquals(51, healthManager.getHealth());

        assertFalse(healthManager.reduceHealth(60));
    }
}
