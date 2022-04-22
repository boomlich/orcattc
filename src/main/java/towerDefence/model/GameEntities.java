package towerDefence.model;

import towerDefence.components.Collision;
import towerDefence.components.Projectile;
import towerDefence.enemies.IEnemy;
import towerDefence.particles.Particle;
import towerDefence.particles.ParticleEmitter;
import towerDefence.tower.ITower;
import towerDefence.view.IRenderableObject;
import towerDefence.view.Interaction.Interactable;
import towerDefence.view.Interaction.InteractionManager;

import java.util.*;

public class GameEntities {

    private List<IEnemy> enemies = new ArrayList<>();
    private List<ITower> towers = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private List<Particle> particles = new ArrayList<>();
    private List<ParticleEmitter> particleEmitters = new ArrayList<>();
    private List<Collision> boardCollisions = new ArrayList<>();

    // Z-depth ordered renderable objects
    private HashMap<Integer, List<IEnemy>> renderEnemies = new HashMap<>();
    private HashMap<Integer, List<ITower>> renderTowers = new HashMap<>();
    private TreeSet<Integer> zDepthRange = new TreeSet<>();

    public GameEntities() {
    }

    public void addBoardCollisions(List<Collision> collisionsObjects) {
        boardCollisions.addAll(collisionsObjects);
    }

    public List<Collision> getBoardCollisions() {
        return boardCollisions;
    }

    private List<IEnemy> sortEnemiesByPathProgression() {

        List<IEnemy> sortedEnemies = new ArrayList<>(List.copyOf(enemies));

        sortedEnemies.sort(new Comparator<IEnemy>() {
            @Override
            public int compare(IEnemy o1, IEnemy o2) {
                return Double.compare(o2.getPathProgression(), o1.getPathProgression());
            }
        });

        return sortedEnemies;
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

    private void updateTowers(double deltaSteps){
        for (ITower tower: towers) {
            tower.update(deltaSteps);
        }
    }

    protected void update(double deltaSteps) {

        enemies = sortEnemiesByPathProgression();

        updateTowers(deltaSteps);

        List<Projectile> deadProjectiles = new ArrayList<>();
        for (Projectile projectile: projectiles) {
            projectile.update(deltaSteps);
            if (projectile.isDead()) {
                deadProjectiles.add(projectile);
            }
        }
        removeDead(deadProjectiles, projectiles);


        List<IEnemy> deadEnemies = new ArrayList<>();
        for (IEnemy enemy: enemies) {
            enemy.update(deltaSteps);
            if (enemy.isDead()) {
                deadEnemies.add(enemy);
            }
        }
        removeDead(deadEnemies, enemies);

        List<Particle> deadParticles = new ArrayList<>();
        for (Particle particle: particles) {
            particle.update(deltaSteps);
            if (particle.isDead()) {
                deadParticles.add(particle);
            }
        }
        particles.removeAll(deadParticles);



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
        InteractionManager.addIntractable((Interactable) tower);
        renderTowers = sortByZDepth(towers);
        System.out.println(renderTowers);
    }

    public HashMap<Integer, List<ITower>> getTowers() {
        return renderTowers;
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public void addParticle(Particle particle) {
        System.out.println("Projectile added");
        particles.add(particle);
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

    public void removeEnemy(IEnemy enemy) {
        enemies.remove(enemy);
    }

    public List<IEnemy> getSortedEnemies() {
        return enemies;
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }
}

