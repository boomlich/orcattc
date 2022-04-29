package towerDefence.model;

import org.junit.jupiter.api.Test;
import towerDefence.enemies.IEnemy;
import towerDefence.enemies.enemyTypes.Ogre;
import towerDefence.enemies.enemyTypes.OrcBrute;
import towerDefence.enemies.enemyTypes.OrcBruteBerserker;
import towerDefence.enemies.enemyTypes.OrcGrunt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameEntitiesTest {

    @Test
    void sortEnemiesByProgressionTest() {

        IEnemy A = new OrcGrunt(50);
        IEnemy B = new OrcBrute(20);
        IEnemy C = new Ogre(100);
        IEnemy E = new OrcBruteBerserker(75);
        IEnemy F = new OrcGrunt(1);
        IEnemy G = new Ogre(200);

        GameEntities gameEntities = new GameEntities();

        gameEntities.addEnemy(A);
        gameEntities.addEnemy(B);
        gameEntities.addEnemy(C);
        gameEntities.addEnemy(E);
        gameEntities.addEnemy(F);
        gameEntities.addEnemy(G);

        List<IEnemy> expectedOrder = new ArrayList<>(Arrays.asList(C, C, E, A, B, A));
        List<IEnemy> sortedEnemies = gameEntities.getSortedEnemies(true);

        for (int i = 0; i < expectedOrder.size(); i++) {
            assertEquals(expectedOrder.get(i).getClass(), sortedEnemies.get(i).getClass());
        }
    }
}
