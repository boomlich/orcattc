package towerDefence.particles;

import towerDefence.view.sprite.Animation;
import towerDefence.view.IRenderableObject;
import towerDefence.view.sprite.Sprite;
import towerDefence.view.sprite.SpriteEngine;

import java.awt.geom.Point2D;

public class Particle implements IRenderableObject {

    private final SpriteEngine sprite;
    private final Point2D position;
    private final Animation animation;
    private int currentTime;
    private boolean isDead = false;

    public Particle(SpriteEngine sprite, Animation particleAnimation, Point2D position, int lifeTime) {
        this.sprite = sprite;
        this.position = position;
        currentTime = lifeTime;
        this.animation = particleAnimation;
        sprite.start(particleAnimation);
    }

    @Override
    public int getZDepth() {
        return 0;
    }

    public Sprite getSprite() {
        return sprite.getSprite();
    }

    public Point2D getPosition() {
        return new Point2D.Double(position.getX(), position.getY());
    }

    public void update(double deltaSteps) {
        currentTime -= 1000/60 * deltaSteps;

        if (currentTime <= 0) {
            isDead = true;
        } else {
            sprite.update(deltaSteps);
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public Particle makeCopyWithPosition(Point2D position) {
        return new Particle(sprite, animation, position, currentTime);
    }
}