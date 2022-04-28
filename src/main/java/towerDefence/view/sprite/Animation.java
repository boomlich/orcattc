package towerDefence.view.sprite;

public class Animation {
    public final int startFrame;
    public final int endFrame;
    public final boolean loop;

    public Animation(int startFrame, int endFrame, boolean loop) {
        this.startFrame = startFrame;
        this.endFrame = endFrame;
        this.loop = loop;
    }
}
