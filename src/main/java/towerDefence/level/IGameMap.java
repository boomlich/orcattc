package towerDefence.level;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.List;

public interface IGameMap {

    public ImageIcon getBackground();

    public List<List<Point2D>> getPaths();

    public EnemyWave getCurrentWave();

}
