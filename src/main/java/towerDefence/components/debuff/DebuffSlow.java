package towerDefence.components.debuff;

import towerDefence.components.Animation;
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
     * Total duration of the slow effect
     */
    private final int totalDuration;

    /**
     * Chance of slow effect to be applied upon hit
     */
    private final double randomChance;

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


    private double limitScope(double inputNumber) {
        if (inputNumber > 1) {
            inputNumber = 1;
        } else if (inputNumber < 0) {
            inputNumber = 0;
        }
        return inputNumber;
    }

    private void randomizeSlowEffect(double speedReduction, double randomChance) {
        Random rand = new Random();
        if (rand.nextDouble() < randomChance) {
            slowMovementSpeed(speedReduction);
        }
    }

    private void slowMovementSpeed(double speedReduction) {
        getTarget().getMovement().setSpeed(speedReduction);
        System.out.println("SLOWED");
    }

    @Override
    protected void effectTrigger() {
        // Restore speed
        removeEffect();
    }

    @Override
    public void removeEffect() {
        super.removeEffect();
        restoreSpeed();
    }

    private void restoreSpeed() {
        getTarget().getMovement().setSpeed(originalSpeed);
        System.out.println("RESTORED");
    }

    @Override
    public IDebuff makeCopy() {
        return new DebuffSlow(totalDuration, speedReduction, randomChance, getSpriteEngine(), getSpriteAnimation(), getIdentifier());
    }

    public double getSlowMultiplier() {
        return speedReduction;
    }
}
