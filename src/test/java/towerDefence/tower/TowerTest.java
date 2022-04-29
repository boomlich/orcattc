package towerDefence.tower;

import org.junit.jupiter.api.Test;
import towerDefence.components.Targeting.Targeting;
import towerDefence.components.Targeting.TargetingMode;
import towerDefence.components.damage.Damage;
import towerDefence.enemies.IEnemy;
import towerDefence.enemies.enemyTypes.Ogre;
import towerDefence.enemies.enemyTypes.OrcBrute;
import towerDefence.enemies.enemyTypes.OrcBruteShielded;
import towerDefence.enemies.enemyTypes.OrcGrunt;
import towerDefence.tower.towerTypes.Archer;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TowerTest {

    @Test
    void updateTargetingDirectionTest() {
        Tower archer = new Archer();
        archer.updatePosition(new Point2D.Double(0, 0));
        assertEquals(2, archer.updateTargetDirection(new Point2D.Double(1, 1)));
        assertEquals(4, archer.updateTargetDirection(new Point2D.Double(5, 0)));
        assertEquals(8, archer.updateTargetDirection(new Point2D.Double(1, -100)));
        assertEquals(17, archer.updateTargetDirection(new Point2D.Double(-1, 100)));
    }

    @Test
    void addStatsTest() {
        ITower archer = new Archer();
        IEnemy orc = new OrcGrunt();
        archer.addStats(orc, 100, new Damage(13));
        assertEquals(13, archer.getDamageDone());
        archer.addStats(orc, 100, new Damage(261));
        assertEquals(13 + 261, archer.getDamageDone());
    }

    @Test
    void targetEnemyTest() {
        Tower archer = new Archer();
        List<IEnemy> enemies = new ArrayList<>(List.of(new OrcGrunt(null), new OrcBrute(null), new Ogre(null), new OrcBruteShielded(null)));

        assertEquals(enemies.get(0).getClass(), archer.targetEnemy(enemies, new Targeting(TargetingMode.FIRST, archer)).getClass());
        assertEquals(enemies.get(3).getClass(), archer.targetEnemy(enemies, new Targeting(TargetingMode.LAST, archer)).getClass());
        assertEquals(enemies.get(2).getClass(), archer.targetEnemy(enemies, new Targeting(TargetingMode.STRONGEST, archer)).getClass());
    }
}
