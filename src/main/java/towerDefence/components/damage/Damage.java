package towerDefence.components.damage;

public class Damage implements IDamage {

    private int damageValue = 100;

    public Damage(int damageValue) {
        this.damageValue = damageValue;
    }

    @Override
    public void applyDamage(IDamageable target) {
        target.applyDamage(damageValue);
    }

    @Override
    public int getDamageValue() {
        return 0;
    }
}
