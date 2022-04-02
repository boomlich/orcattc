package towerDefence.particles;

import javax.swing.*;
import java.util.Random;

public class ParticleEmitter {

    // Total amount of time to emit particles. If set to zero
    private double emitterLifespan;

    // Minimum and maximum time the particle will exist after spawning.
    private double timeToLiveMin, timeToLiveMax;

    // Maximum and minimum forces in x and y direction.
    private double minForcePosX, maxForcePosX, minForceNegX, maxForceNegX;
    private double minForcePosY, maxForcePosY, minForceNegY, maxForceNegY;

    // Minimum and maximum size of the particle upon spawning.
    private double initSizeMin, initSizeMax;

    // Minimum and maximum size of the particle upon death
    private double endSizeMin, getEndSizeMax;

    // The total amount of particles spawned in the emitters' lifespan.
    // If lifespan of emitter set to 0, this regulates how many particles
    // is emitted per second.
    private int emitterCount;

    ImageIcon sprite;

    public ParticleEmitter(ImageIcon sprite, double emitterLifespan, double timeToLiveMin, double timeToLiveMax,
                           double minForcePosX, double maxForcePosX, double minForceNegX, double maxForceNegX,
                           double minForcePosY, double maxForcePosY, double minForceNegY, double maxForceNegY,
                           double initSizeMin, double initSizeMax, double endSizeMin, double getEndSizeMax,
                           int emitterCount) {
        this.sprite = sprite;
        this.emitterLifespan = emitterLifespan;
        this.timeToLiveMin = timeToLiveMin;
        this.timeToLiveMax = timeToLiveMax;
        this.minForcePosX = minForcePosX;
        this.maxForcePosX = maxForcePosX;
        this.minForceNegX = minForceNegX;
        this.maxForceNegX = maxForceNegX;
        this.minForcePosY = minForcePosY;
        this.maxForcePosY = maxForcePosY;
        this.minForceNegY = minForceNegY;
        this.maxForceNegY = maxForceNegY;
        this.initSizeMin = initSizeMin;
        this.initSizeMax = initSizeMax;
        this.endSizeMin = endSizeMin;
        this.getEndSizeMax = getEndSizeMax;
        this.emitterCount = emitterCount;
    }

    private void spawnParticle() {
        Random rand = new Random();
    }
}
