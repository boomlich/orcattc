package towerDefence.view.sprite;

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
            image = loadSpriteSheet(path);
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

    private BufferedImage loadSpriteSheet(String path) throws IOException {
        return ImageIO.read(ClassLoader.getSystemResource( path ));
    }

    public BufferedImage grabSprite(int spriteIndex) {
        Point spritePosition = convertIndexToRowCol(spriteIndex);
        int spriteWidth = image.getWidth() / columns;
        int spriteHeight = image.getHeight() / rows;

//        System.out.println("GRABBED ::::::" + spriteIndex + "       :::       " + spritePosition + "       :::       " + spritePosition.x * spriteHeight + " ... " + spritePosition.y * spriteWidth);

        return image.getSubimage(spritePosition.x * spriteHeight, spritePosition.y * spriteWidth, spriteWidth, spriteHeight);
    }

    protected Point convertIndexToRowCol(int index) {
        int row = index / columns;
        int col = index % columns;

        return new Point(row, col);
    }
}
