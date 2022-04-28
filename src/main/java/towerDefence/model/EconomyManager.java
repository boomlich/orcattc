package towerDefence.model;

public class EconomyManager {

    private int money;

    public EconomyManager(int startMoney) {
        money = startMoney;
    }

    /**
     * @param requirement money required to purchase item
     * @return true if sufficient funds for the purchase
     */
    public boolean hasSufficientFunds(int requirement) {
        return money >= requirement;
    }

    /**
     * Complete transaction if sufficient funds. The money required is
     * subtracted from the available money.
     *
     * @param requirement required money for the target item.
     * @return true if purchase transaction was completed and valid
     */
    protected boolean purchaseItem(int requirement) {
        if (hasSufficientFunds(requirement)) {
            money -= requirement;
            return true;
        }
        return false;
    }

    /**
     * @param newMoney Money to be added to the inventory
     */
    protected void addMoney(int newMoney) {
        this.money += newMoney;
        System.out.println(newMoney);
    }

    /**
     * @return total amount of money available in the inventory.
     */
    public int getMoney(){
        return money;
    }
}

