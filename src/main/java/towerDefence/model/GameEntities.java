package towerDefence.model;

import towerDefence.components.Collision.Collision;
import towerDefence.components.Weapons.Projectile;
import towerDefence.enemies.IEnemy;
import towerDefence.particles.Particle;
import towerDefence.particles.ParticleEmitter;
import towerDefence.tower.ITower;
import towerDefence.view.IRenderableObject;
import UI.Interaction.Interactable;
import UI.Interaction.InteractionManager;

import java.util.*;

public class GameEntities {

    private List<IEnemy> enemies = new ArrayList<>();
    private List<ITower> towers = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private List<Particle> particles = new ArrayList<>();
    private List<ParticleEmitter> particleEmitters = new ArrayList<>();
    private List<Collision> boardCollisions = new ArrayList<>();

    /**
     * Enemies sorted by z-depth for rendering the enemies in correct order.
     */
    private HashMap<Integer, List<IEnemy>> renderEnemies = new HashMap<>();

    /**
     * Towers sorted by z-depth for rendering the enemies in correct order.
     */
    private HashMap<Integer, List<ITower>> renderTowers = new HashMap<>();

    /**
     * All the z-depth values of the enemies and towers on the current frame.
     */
    private TreeSet<Integer> zDepthRange = new TreeSet<>();

    /**
     * Money earned since last update. Money earned from enemies being killed.
     */
    private int moneyEarned = 0;

    /**
     * Damage done to the player since last update. Increased by
     * enemies reaches the end of the path.
     */
    private int damageDone = 0;

    public GameEntities() {
    }

    public void addBoardCollisions(List<Collision> collisionsObjects) {
        boardCollisions.addAll(collisionsObjects);
    }

    public List<Collision> getBoardCollisions() {
        return boardCollisions;
    }

    /**
     *
     * @return sorted list of enemies. Enemy with most progression is first in list.
     */
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
     * @param objects list of objects to be sorted by z-depth
     * @return HashMap with z-depth values as keys, and list of the objects on the
     * corrosponding z-depth value as values.
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

        enemies = getSortedEnemies(true);

        // Update all game entities
        updateRenderableObject(towers, deltaSteps);
        updateRenderableObject(projectiles, deltaSteps);
        updateRenderableObject(enemies, deltaSteps);
        updateRenderableObject(particles, deltaSteps);

        // Sort enemies by z-depth
        renderEnemies = sortByZDepth(enemies);
    }

    /**
     * Update all objects in list. Remove any objects that have died
     * after updating.
     *
     * @param renderableObjects objects to be updated
     * @param deltaSteps
     */
    private <T extends IRenderableObject> void updateRenderableObject(List<T> renderableObjects, double deltaSteps) {

        List<T> deadEntities = new ArrayList<>();
        for (T entity: renderableObjects) {
            entity.update(deltaSteps);
            if (entity.isDead()) {
                if (entity instanceof IEnemy) {
                    checkEnemyMoneyOrDamage((IEnemy) entity);
                }
                deadEntities.add(entity);
            }
        }
        renderableObjects.removeAll(deadEntities);
    }

    private void checkEnemyMoneyOrDamage(IEnemy enemy) {
        if (enemy.hasReachedEnd()) {
            damageDone += enemy.getReachedEndDamage();
        } else {
            moneyEarned += enemy.getMoneyLoot();
        }
    }

    /**
     * @return set of z-depth values where renderable objects are present.
     */
    public TreeSet<Integer> getzDepthRange() {
        return zDepthRange;
    }

    /**
     * @return HashMap of all enemies sorted by their z-depth value.
     * The HashMap keys are active z-depth values, and the HashMap values
     * are list of enemies on the corresponding z-depth value.
     */
    public HashMap<Integer, List<IEnemy>> getEnemies() {
        return renderEnemies;
    }

    /**
     * @return HashMap of all towers sorted by their z-depth value.
     * The HashMap keys are active z-depth values, and the HashMap values
     * are list of towers on the corresponding z-depth value.
     */
    public HashMap<Integer, List<ITower>> getTowers() {
        return renderTowers;
    }



    /**
     * Add tower to the map. Activates the tower for mouse
     * interaction.
     *
     * @param tower target tower te be added
     */
    public void addTower(ITower tower){
        towers.add(tower);
        InteractionManager.addIntractable((Interactable) tower);
        renderTowers = sortByZDepth(towers);
    }

    /**
     * @return all currently active projectiles on the map
     */
    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    /**
     * @return all currently active particles on the map
     */
    public List<Particle> getParticles() {
        return particles;
    }

    public void addParticle(Particle particle) {
        particles.add(particle);
    }

    public void addEnemy(IEnemy enemy) {
        enemies.add(enemy);
    }

    /**
     * @return list of enemies sorted by their progress on
     * the traversal path. Sorted from the enemy with
     * the most progress to the least progress.
     */
    public List<IEnemy> getSortedEnemies(boolean sortEnemiesNow) {
        if (sortEnemiesNow) {
            return sortEnemiesByPathProgression();
        }
        return enemies;
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    /**
     * Removes tower from the map and disabled the tower
     * from interaction.
     *
     * @param tower target tower to be removed.
     */
    public void removeTower(ITower tower) {
        towers.remove(tower);

        Interactable interactable = (Interactable) tower;
        interactable.setInactive();
        InteractionManager.removeInactive();
        renderTowers = sortByZDepth(towers);
    }

    /**
     * @return damage done to the player since last update.
     */
    public int getDamageDone() {
        int returnDamage = damageDone;
        damageDone = 0;
        return returnDamage;
    }

    /**
     * @return money earned from killing enemies since last update
     */
    public int getMoneyEarned() {
        int loot = moneyEarned;
        moneyEarned = 0;
        return loot;
    }
}

