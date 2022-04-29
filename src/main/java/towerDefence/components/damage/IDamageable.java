package towerDefence.components.damage;

public interface IDamageable {

    /**
     * Apply the damage to the Damage object on the health
     * of the damageble object.
     *
     * @param damage damage to apply
     */
    void applyDamage(Damage damage);
}
