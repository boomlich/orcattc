package towerDefence.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    public static BufferedImage loadBufferedImage(String path) throws IOException {
        return ImageIO.read(ClassLoader.getSystemResource( path ));
    }

}
