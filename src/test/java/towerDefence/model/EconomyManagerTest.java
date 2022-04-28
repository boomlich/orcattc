package towerDefence.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EconomyManagerTest {

    @Test
    void sufficientFundsTest() {
        EconomyManager economyManager = new EconomyManager(500);

        assertTrue(economyManager.hasSufficientFunds(300));
        assertTrue(economyManager.hasSufficientFunds(500));
        assertFalse(economyManager.hasSufficientFunds(501));
    }

    @Test
    void purchaseItemTest() {
        EconomyManager economyManager = new EconomyManager(500);

        // Insufficient funds
        assertFalse(economyManager.purchaseItem(700));

        // Purchase first item
        assertTrue(economyManager.purchaseItem(365));
        assertEquals(500-365, economyManager.getMoney());

        // Purchase second item
        assertFalse(economyManager.purchaseItem(250));
        assertTrue(economyManager.purchaseItem(100));
        assertEquals(500-365-100, economyManager.getMoney());
    }
}
