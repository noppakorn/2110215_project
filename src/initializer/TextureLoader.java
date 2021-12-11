package initializer;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;


/**
 * Provides Texture loading for the game. This class reduces the need for loading of the same texture multiple time.
 */
public class TextureLoader {
    private Map<String, Image> images;

    /**
     * Instantiates a new Texture loader.
     */
    public TextureLoader() {
        images = new HashMap<>();
    }

    /**
     * If the image exists in the HashMap return the Image.
     * If the image doesn't exist, load the image from disk then put the image in the HashMap.
     *
     * @param resourceName the name of the resources
     * @return the image
     */
    public Image getImage(String resourceName) {
        if (images.containsKey(resourceName)) {
            return images.get(resourceName);
        } else {
            System.out.println(resourceName + ".png");
            Image image = new Image(ClassLoader.getSystemResource(resourceName + ".png").toString());
            images.put(resourceName, image);
            return image;
        }
    }
}
