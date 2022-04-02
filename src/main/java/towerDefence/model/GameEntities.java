package towerDefence.model;

import towerDefence.components.Projectile;
import towerDefence.enemies.IEnemy;
import towerDefence.particles.Particle;
import towerDefence.particles.ParticleEmitter;
import towerDefence.tower.ITower;

import java.util.ArrayList;
import java.util.List;

public class GameEntities {

    private List<IEnemy> enemies = new ArrayList<>();
    private List<ITower> towers = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private List<Particle> particles = new ArrayList<>();
    private List<ParticleEmitter> particleEmitters = new ArrayList<>();

    public GameEntities() {
    }

    private List<IEnemy> sortEnemiesByProgression() {
        return null;
    }

    protected void update(long steps) {

    }

    protected void setEnemies(List<IEnemy> enemies) {
        this.enemies = enemies;
    }

    public List<IEnemy> getEnemies() {
        return enemies;
    }

    protected void setProjectiles(List<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    protected void setTowers(List<ITower> towers) {
        this.towers = towers;
    }

    public List<ITower> getTowers() {
        return towers;
    }

    public List<Particle> getParticles() {
        return particles;
    }

    protected void setParticleEmitters(List<ParticleEmitter> particleEmitters) {
        this.particleEmitters = particleEmitters;
    }

    public List<ParticleEmitter> getParticleEmitters() {
        return particleEmitters;
    }

    protected void setParticles(List<Particle> particles) {
        this.particles = particles;
    }

    protected void clear() {
    }

    public void addEnemy(IEnemy enemy) {
        enemies.add(enemy);
    }
}

