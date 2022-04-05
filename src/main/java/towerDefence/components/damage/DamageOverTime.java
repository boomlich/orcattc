package towerDefence.components.damage;

public class DamageOverTime implements IDamage {

    private int damage = 100;
    private int duration;
    private int totalTicks;
    private double currentTick;
    IDamageable target;

    public DamageOverTime(int totalDamage, int totalTicks, int duration, IDamageable target) {
        this.damage = totalDamage;
        this.totalTicks = totalTicks;
        this.duration = duration;
        this.target = target;
    }

    public void update(double deltaSteps) {
        currentTick += deltaSteps;

    }


    public void applyDamage(IDamageable target) {
    }

    @Override
    public int getDamageValue() {
        return 0;
    }
}
