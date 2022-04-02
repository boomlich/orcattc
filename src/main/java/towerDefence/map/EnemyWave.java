package towerDefence.map;

import towerDefence.enemies.IEnemy;
import towerDefence.enemies.enemyTypes.*;

import java.util.ArrayList;
import java.util.List;

public class EnemyWave {

    private final List<EnemyWithTimer> enemies = new ArrayList<>();


    public EnemyWave(String wave) {
        parseWave(wave);
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
    protected void parseWave(String wave) {
        int selectedTimeDelay = 10;

        for (String section: wave.split(", ")) {

            String[] sectionSplit = section.split("_");
            String command = sectionSplit[0];
            int multiplier = Integer.parseInt(sectionSplit[1]);

            if (command.equals("D")){
                selectedTimeDelay = multiplier;
            } else if (command.equals("P")) {
                enemies.add(new EnemyWithTimer(new EmptyEnemy(), multiplier));
            } else {
                for (int i = 0; i < multiplier; i++) {
                    for (char enemyCode: command.toCharArray()) {
                        enemies.add(new EnemyWithTimer(parseEnemy(enemyCode), selectedTimeDelay));
                    }
                }
            }
        }
    }

    protected IEnemy parseEnemy(char enemyCode){
        IEnemy selectedEnemy = null;

        switch (enemyCode) {
            case 'a' -> selectedEnemy = new RowBoat();
            case 'b' -> selectedEnemy = new Ship();
            case 'c' -> selectedEnemy = new AttackShip();
            case 'd' -> selectedEnemy = new BattleShip();
            case 'e' -> selectedEnemy = new WarShip();
        }
        return selectedEnemy;
    }


    public boolean isEmpty() {
        return enemies.size() == 0;
    }

    public List<EnemyWithTimer> getEnemies(){
        return enemies;
    }

    public EnemyWithTimer getAndRemoveFirstEnemy() {
        EnemyWithTimer selectedEnemy = enemies.get(0);
        enemies.remove(0);

        return selectedEnemy;
    }
}
