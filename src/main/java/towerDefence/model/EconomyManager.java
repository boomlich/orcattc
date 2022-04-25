package towerDefence.model;

public class EconomyManager {

    private int money;

    public EconomyManager(int startMoney) {
        money = startMoney;
    }

    public boolean hasSufficiantFunds(int requirement) {
        return money >= requirement;
    }

    protected boolean purchaseItem(int requirement) {
        if (hasSufficiantFunds(requirement)) {
            money -= requirement;
            return true;
        }
        return false;
    }

    protected void addMoney(int newMoney) {
        this.money += newMoney;
        System.out.println(newMoney);
    }

    public int getMoney(){
        return money;
    }
}

