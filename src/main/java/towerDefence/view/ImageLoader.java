package towerDefence.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    /**
     *
     * @param path file path for the image
     * @return BufferedImage graphics object
     * @throws IOException if object not found
     */
    public static BufferedImage loadBufferedImage(String path) throws IOException {
        return ImageIO.read(ClassLoader.getSystemResource( path ));
    }

}
