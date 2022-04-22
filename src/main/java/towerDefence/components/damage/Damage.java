package towerDefence.components.damage;

public class Damage implements IDamage {

    private int damage = 100;

    public Damage() {
    }

    public Damage(int damage) {
        this.damage = damage;
    }

    @Override
    public int applyDamage(int health) {
        return health - damage;
    }

    @Override
    public int getDamageValue() {
        return damage;
    }
}
