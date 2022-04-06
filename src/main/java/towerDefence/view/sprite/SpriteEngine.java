package towerDefence.view.sprite;

public class SpriteEngine {

    private Sprite sprite;
    private final SpriteSheet spriteSheet;

    /** Default frame from the sprite-sheet displayed */
    private final int defaultFrame;

    /** Playback speed of the sprite animation.
     * How many times per second the sprite should update. */
    private final int spriteFPS;

    /** Range of the selected animation */
    private int startFrame, endFrame;

    /** Previously displayed frame from the current animation playback. */
    private int previousFrame;

    /** Currently displayed frame from the current animation playback.
     * Incremented by the deltaSteps * (spriteFPS / gameFPS) each step */
    double currentFrame;

    /** Determine if an animation should play */
    private boolean activeAnimation = false;

    /** Determines if the currently active animation is looping */
    private boolean loopAnimation = false;

    public SpriteEngine(String path, int rows, int columns, int spriteFPS, int defaultFrame) {
        this.spriteSheet = new SpriteSheet(path, rows, columns);
        this.spriteFPS = spriteFPS;
        this.defaultFrame = defaultFrame;
        setDefaultSprite();
    }

    /**
     * Start an animation within a range.
     *
     * @param startFrame First frame on the sprite-sheet of the animation
     * @param endFrame End frame of the sprite-sheet of the animation
     * @param loopAnimation set true to enable looping of the animation
     */
    public void start(int startFrame, int endFrame, boolean loopAnimation) {
        activeAnimation = true;
        this.startFrame = startFrame;
        currentFrame = startFrame;
        previousFrame = startFrame - 1;
        this.endFrame = endFrame + 2;
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
        // Only update sprite at the spriteFPS playback speed
        if (currentFrame > previousFrame) {
            sprite = new Sprite(spriteSheet.grabSprite((int) currentFrame));
            previousFrame = (int) currentFrame + 1;
        }

        currentFrame += (deltaSteps * spriteFPS/60);
        if (currentFrame >= endFrame) {
            if (loopAnimation) {
                currentFrame = startFrame;
                previousFrame = startFrame - 1;
            } else {
                stop();
            }
        }
    }

    public void setSpriteRotation(double angle) {
        sprite.setRotation(angle);
    }

    public void setDefaultSprite() {
        sprite = new Sprite(spriteSheet.grabSprite(defaultFrame));
    }

    public Sprite getSprite() {
        return sprite;
    }
}
