package towerDefence.model;

import towerDefence.components.Projectile;
import towerDefence.enemies.IEnemy;
import towerDefence.particles.Particle;
import towerDefence.particles.ParticleEmitter;
import towerDefence.tower.ITower;
import towerDefence.tower.towerTypes.Rifleman;
import towerDefence.view.IRenderableObject;

import java.awt.geom.Point2D;
import java.util.*;

public class GameEntities {

    private List<IEnemy> enemies = new ArrayList<>();
    private List<ITower> towers = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private List<Particle> particles = new ArrayList<>();
    private List<ParticleEmitter> particleEmitters = new ArrayList<>();

    // Z-depth ordered renderable objects
    private HashMap<Integer, List<IEnemy>> renderEnemies;
    private HashMap<Integer, List<ITower>> renderTowers;
    private TreeSet<Integer> zDepthRange = new TreeSet<>();

    public GameEntities() {

        addTower(new Rifleman(new Point2D.Double(325, 250)));

    }

    private List<IEnemy> sortEnemiesByProgression() {


        return null;
    }

    /**
     * Take in renderable objects and sort them by their z-depth value, such that
     * when rendering the objects on screen, they are rendered in the correct order.
     *
     * Sort the object in a HashMap, where each key corresponds to the z-depth value
     * of at least one of the objects. The corresponding value in the HashMap is a
     * list of all objects with the matching z-depth.
     *
     *
     * @param objects
     * @param <T>
     * @return
     */
    private <T extends IRenderableObject> HashMap<Integer, List<T>> sortByZDepth(List<T> objects){
        HashMap<Integer, List<T>> sortedObjects = new HashMap<>();

        for (T object: objects) {
            int zDepth = object.getZDepth();
            if (!sortedObjects.containsKey(zDepth)) {
                sortedObjects.put(zDepth, new ArrayList<T>(List.of(object)));
                zDepthRange.add(zDepth);
            } else {
                sortedObjects.get(zDepth).add(object);
            }
        }
        return sortedObjects;
    }

    protected void update(double deltaSteps) {

        List<IEnemy> deadEnemies = new ArrayList<>();

        for (IEnemy enemy: enemies) {
            enemy.update(deltaSteps);
            if (enemy.isDead()) {
                deadEnemies.add(enemy);
            }
        }

        removeDead(deadEnemies, enemies);

        // Sort enemies by z-depth
        renderEnemies = sortByZDepth(enemies);
    }

    private <T> void removeDead(List<T> deadList, List<T> originalList) {
        for (T object: deadList) {
            originalList.remove(object);
        }
    }

    public TreeSet<Integer> getzDepthRange() {
        return zDepthRange;
    }

    protected void setEnemies(List<IEnemy> enemies) {
        this.enemies = enemies;
    }

    public HashMap<Integer, List<IEnemy>> getEnemies() {
        return renderEnemies;
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

    public void addTower(ITower tower){
        towers.add(tower);
        renderTowers = sortByZDepth(towers);
        System.out.println(renderTowers);
    }

    public HashMap<Integer, List<ITower>> getTowers() {
        return renderTowers;
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
        System.out.println("Enemy added:     " + enemy);
    }

    public void removeEnemy(IEnemy enemy) {
        enemies.remove(enemy);
    }
}

