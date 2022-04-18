package towerDefence.components.damage;

public class Damage implements IDamage {

    private int damage = 100;
    private int duration = 0;
    private int totalTicks = 0;

    public Damage(int totalDamage, int totalTicks, int duration) {
        this.damage = totalDamage;
        this.totalTicks = totalTicks;
        this.duration = duration;
    }

    public Damage() {
    }

    public Damage(int damage) {
        this.damage = damage;
    }

    public boolean isDamageOverTime() {
        return duration != 0;
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
