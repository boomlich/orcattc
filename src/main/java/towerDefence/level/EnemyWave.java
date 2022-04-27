package towerDefence.level;

import towerDefence.enemies.IEnemy;
import towerDefence.enemies.enemyTypes.*;
import towerDefence.level.path.SplinePathData;

import java.util.ArrayList;
import java.util.List;

public class EnemyWave {

    private List<EnemyWithSpawnTime> enemies = new ArrayList<>();
    private SplinePathData path;
    private double totalPathLength;
    private int startSpawnTimer = 0;

    public EnemyWave(String wave, SplinePathData path) {
        this.path = path;
        enemies = parseWave(wave);
    }

    // For testing
    public EnemyWave() {
    }

    /**
     * Takes a specially formatted string with information about spawn delay,
     * pause delay, and types of enemies in order, and converts to list of
     * delay-times and a list of enemies in their spawning order.
     *
     * String sections are seperated by commas ', ' and are
     * formatted like: "command_x"
     * Commands
     * D = Delay for x steps between subsequent enemies spawning
     * P = Pause spawning for x amount of steps
     * a, b, c, d, e = enemy types
     *
     * @param wave formatted string with wave information
     */
    private List<EnemyWithSpawnTime> parseWave(String wave) {
        int selectedTimeDelay = 10;
        List<EnemyWithSpawnTime> enemies = new ArrayList<>();

        for (String section: wave.split(", ")) {

            String[] sectionSplit = section.split("_");
            String command = sectionSplit[0];
            int multiplier = Integer.parseInt(sectionSplit[1]);

            if (command.equals("D")){
                selectedTimeDelay = multiplier;
            } else if (command.equals("P")) {
                if (enemies.size()== 0) {
                    startSpawnTimer = multiplier;
                } else {
                    enemies.get(enemies.size()-1).setTimer(multiplier);
                }
            } else {
                for (int i = 0; i < multiplier; i++) {
                    for (char enemyCode: command.toCharArray()) {
                        enemies.add(new EnemyWithSpawnTime(parseEnemy(enemyCode), selectedTimeDelay));
                    }
                }
            }
        }
        return enemies;
    }

    /**
     *
     * @param enemyCode letter corrosponding to the type of enemy
     * @return enemy
     */
    protected IEnemy parseEnemy(char enemyCode){
        IEnemy selectedEnemy = null;

        switch (enemyCode) {
            case 'a' -> selectedEnemy = new OrcGrunt(path);
            case 'b' -> selectedEnemy = new OrcGruntShielded(path);
            case 'c' -> selectedEnemy = new OrcBrute(path);
            case 'd' -> selectedEnemy = new OrcBruteShielded(path);
            case 'e' -> selectedEnemy = new OrcGruntBerserker(path);
            case 'f' -> selectedEnemy = new OrcGruntBerserkerShielded(path);
            case 'g' -> selectedEnemy = new OrcBruteBerserker(path);
            case 'h' -> selectedEnemy = new OrcBruteBerserkerShielded(path);
            case 'i' -> selectedEnemy = new Ogre(path);
        }
        return selectedEnemy;
    }


    public boolean notEmpty() {
        return enemies.size() != 0;
    }

    public List<EnemyWithSpawnTime> getEnemies(){
        return enemies;
    }

    public EnemyWithSpawnTime getAndRemoveFirstEnemy() {
        EnemyWithSpawnTime selectedEnemy = enemies.get(0);
        enemies.remove(0);
        return selectedEnemy;
    }

    public int getStartSpawnTimer() {
        return startSpawnTimer;
    }
}
