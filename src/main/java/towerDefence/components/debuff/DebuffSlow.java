package towerDefence.components.debuff;

import towerDefence.view.sprite.Animation;
import towerDefence.enemies.IEnemy;
import towerDefence.view.sprite.SpriteEngine;

import java.util.Random;

public class DebuffSlow extends Debuff{

    /**
     * Reduction of the enemy speed in percentage. Value from 0-1.
     * 0.5 reduce the targets speed by 50%.
     */
    private final double speedReduction;

    /**
     * Total duration of the slow effect.
     */
    private final int totalDuration;

    /**
     * Chance of slow effect to be applied upon hit.
     */
    private final double randomChance;

    /**
     * Speed prior to slow effect getting applied.
     */
    private double originalSpeed;

    public DebuffSlow(int duration, double speedReduction, double randomChance, SpriteEngine spriteEngine, Animation animation, String identifier) {
        super(duration, identifier);
        this.totalDuration = duration;
        setSpriteEngine(spriteEngine, animation);

        this.speedReduction = limitScope(speedReduction);
        this.randomChance = limitScope(randomChance);
    }

    @Override
    public void setTarget(IEnemy target) {
        super.setTarget(target);
        originalSpeed = target.getMovement().getSpeed();
        double reducedSpeed = originalSpeed * speedReduction;

        randomizeSlowEffect(reducedSpeed, randomChance);
    }

    /**
     * Takes the given input and validate that it is within 0-1. If
     * inputNumber > 1, return 1. If inputNumber < 0, return 0.
     *
     * @param inputNumber desired value
     * @return inputNumber with limit between 0-1
     */
    private double limitScope(double inputNumber) {
        if (inputNumber > 1) {
            inputNumber = 1;
        } else if (inputNumber < 0) {
            inputNumber = 0;
        }
        return inputNumber;
    }

    /**
     * @param speedReduction desired speed reduction in percentage
     * @param randomChance percentage change of slow effect getting applied
     */
    private void randomizeSlowEffect(double speedReduction, double randomChance) {
        Random rand = new Random();
        if (rand.nextDouble() < randomChance) {
            slowMovementSpeed(speedReduction);
        }
    }

    private void slowMovementSpeed(double speedReduction) {
        getTarget().getMovement().setSpeed(speedReduction);
    }

    @Override
    protected void effectTrigger() {
        removeEffect();
    }

    @Override
    public void removeEffect() {
        super.removeEffect();
        restoreSpeed();
    }

    private void restoreSpeed() {
        getTarget().getMovement().setSpeed(originalSpeed);
    }

    @Override
    public IDebuff makeCopy() {
        return new DebuffSlow(totalDuration, speedReduction, randomChance, getSpriteEngine(), getSpriteAnimation(), getIdentifier());
    }

    /**
     * @return percentage slowdown to the movement speed. Value from 0-1.
     */
    public double getSlowMultiplier() {
        return speedReduction;
    }
}
