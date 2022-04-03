package towerDefence.view.sprite;

public class SpriteEngine {

    private Sprite sprite;
    private final SpriteSheet spriteSheet;
    private final int defaultFrame;
    private int startFrame, endFrame;
    double currentFrame;
    private boolean activeAnimation = false;
    private boolean loopAnimation = false;

    public SpriteEngine(String path, int rows, int columns, int defaultFrame) {
        this.spriteSheet = new SpriteSheet(path, rows, columns);
        this.defaultFrame = defaultFrame;
        setDefaultSprite();
    }

    public void start(int startFrame, int endFrame, boolean loopAnimation) {
        activeAnimation = true;
        this.startFrame = startFrame;
        currentFrame = startFrame;
        this.endFrame = endFrame;
        this.loopAnimation = loopAnimation;

        playAnimation(1);
    }

    public void stop() {
        activeAnimation = false;
    }

    public void update(double deltaSteps) {
        if (activeAnimation) {
            playAnimation(deltaSteps);
        }
    }

    private void playAnimation(double deltaSteps) {
        sprite = new Sprite(spriteSheet.grabSprite((int) currentFrame));

        currentFrame += (deltaSteps * 0.05);
        System.out.println((int) currentFrame);



        if (currentFrame >= endFrame) {
            if (loopAnimation) {
                currentFrame = startFrame;
            } else {
                stop();
            }
        }
    }

    public void setDefaultSprite() {
        sprite = new Sprite(spriteSheet.grabSprite(defaultFrame));
    }

    public Sprite getSprite() {
        return sprite;
    }
}
