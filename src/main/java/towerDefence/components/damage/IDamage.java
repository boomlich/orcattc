package towerDefence.components.damage;

public interface IDamage {

    /**
     * Take the input health value, reduce it by the damageValue of
     * the Damage.
     *
     * @param health health value of the object to be damaged
     * @return health value of the object after damaging
     */
    int applyDamage(int health);

    /**
     * @return the amount of damage that will be done if applied
     */
    int getDamageValue();
}
