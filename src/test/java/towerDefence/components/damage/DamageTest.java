package towerDefence.components.damage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DamageTest {


    @Test
    void applyDamageTest() {
        Damage testDamage = new Damage(10);
        int health = 100;
        assertEquals(health-10, testDamage.applyDamage(health));

        testDamage = new Damage(100);
        assertEquals(0, testDamage.applyDamage(health));
    }
}
