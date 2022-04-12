package towerDefence.view.sprite;

import towerDefence.view.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    BufferedImage image;
    int rows;
    int columns;

    public SpriteSheet(String path, int rows, int columns) {
        try {
            image = ImageLoader.loadBufferedImage(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.rows = rows;
        this.columns = columns;
    }

    protected SpriteSheet(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * Retrieve the sprite at a position in the sprite-sheet.
     * @param spriteIndex Frame-number of the selected sprite in the sprite-sheet
     * @return
     */
    public BufferedImage grabSprite(int spriteIndex) {
        Point spritePosition = convertIndexToRowCol(spriteIndex);
        int spriteWidth = image.getWidth() / columns;
        int spriteHeight = image.getHeight() / rows;

        return image.getSubimage(spritePosition.y * spriteWidth,  spritePosition.x * spriteHeight, spriteWidth, spriteHeight);
    }

    /**
     * Convert the index of a sprite to its corresponding row and column in the sprite-sheet
     *
     * @param index of the spire in the sprite-sheet
     * @return Point with row and column coordinates
     */
    protected Point convertIndexToRowCol(int index) {
        int row = index / columns;
        int col = index % columns;

        return new Point(row, col);
    }
}
