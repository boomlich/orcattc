package towerDefence.components.damage;

public interface IDamage {

    public void applyDamage(IDamageable target);

    public int getDamageValue();
}
