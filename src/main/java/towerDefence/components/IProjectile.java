package towerDefence.components;

import towerDefence.model.GameEntities;
import towerDefence.tower.ITower;

import java.awt.geom.Point2D;

public interface IProjectile {

    public Projectile makeCopy();

    public void fireProjectile(Point2D spawn, Point2D target, ITower towerOwner, GameEntities gameEntities);

    public void increasePenetration(int penetrationDelta);

    public void increaseDamage(double percentageDelta);

}
