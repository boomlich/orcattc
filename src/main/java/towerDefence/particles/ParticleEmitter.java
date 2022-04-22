package towerDefence.particles;

import towerDefence.model.GameEntities;

import java.awt.geom.Point2D;

public class ParticleEmitter {


    private Particle particle;
    private int emitterTimer;
    private int currentTime;
    private GameEntities gameEntities;
    private boolean isDead = false;
    private Point2D particleSpawnPosition = new Point2D.Double(0, 0);

    public ParticleEmitter(Particle particle, int emitterTimer) {
        this.particle = particle;
        this.emitterTimer = emitterTimer;
    }

    public void update(double deltaSteps, Point2D position) {
        if (!isDead) {
            currentTime -= 1000/60 * deltaSteps;

            if (currentTime <= 0) {
                spawnParticle();
                currentTime = emitterTimer;
            }
            particleSpawnPosition = position;
        }
    }

    public void setGameEntities(GameEntities gameEntities) {
        this.gameEntities = gameEntities;
    }

    public void disableEmitter() {
        isDead = true;
    }

    private void spawnParticle() {
        gameEntities.addParticle(particle.makeCopyWithPosition(particleSpawnPosition));
    }

    public ParticleEmitter makeCopy() {
        return new ParticleEmitter(particle.makeCopyWithPosition(particle.getPosition()), emitterTimer);
    }
}
