package towerDefence.level;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.List;

public class MapA implements IGameMap{

    private final String[] enemyWaves = {
            "D_15, a_2, db_1, P_60, abc_1",
    };
    private Point2D[] pathCornerPoints;

    public MapA() {
    }

    @Override
    public ImageIcon getBackground() {
        return null;
    }

    @Override
    public List<List<Point2D>> getPaths() {
        return null;
    }

    @Override
    public EnemyWave getCurrentWave() {
        return new EnemyWave(enemyWaves[0]);
    }
}
