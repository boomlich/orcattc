package towerDefence.model;

public class HealthManager {

    private int health;

    public HealthManager(int startHealth) {
        this.health = startHealth;
    }

    /**
     * @param deltaHealth change in health
     * @return return true if health successfully reduced and still above 0 after reduction. False if dead.
     */
    protected boolean reduceHealth(int deltaHealth) {
        health -= deltaHealth;
        return health > 0;
    }

    /**
     * @return current health
     */
    protected int getHealth() {
        return health;
    }
}
