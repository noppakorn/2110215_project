package initializer;

import application.GameController;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;


/**
 * Provides Texture loading for the game. This class reduces the need for loading of the same texture multiple time.
 */
public class TextureLoader {
    private Map<String, Image> images;
    private Map<String, WritableImage> blockImages;
    private PixelReader blockTextureReader;
    private Map<String, Pair<Integer, Integer>> blockNameToPos;

    /**
     * Instantiates a new Texture loader.
     */
    public TextureLoader() {
        images = new HashMap<>();
        blockImages = new HashMap<>();
        blockTextureReader  = new Image(ClassLoader.getSystemResource("MinecraftTexture.png").toString()).getPixelReader();
        blockNameToPos = new HashMap<>();
        blockNameToPos.put("Dirt", new Pair<>(2, 0));
        blockNameToPos.put("Grass", new Pair<>(3, 0));
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
    public WritableImage getBlockImage(String blockName) {
        if (blockImages.containsKey(blockName)) {
            return blockImages.get(blockName);
        } else {
            Pair<Integer, Integer> pos = blockNameToPos.get(blockName);
            WritableImage image = new WritableImage(blockTextureReader, 128 * pos.getKey(),128 * pos.getValue(),128,128);
            blockImages.put(blockName, image);
            return image;
        }
    }
}
