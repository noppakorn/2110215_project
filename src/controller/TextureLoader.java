package controller;

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
    /**
     * Stores already loaded images
     */
    private Map<String, Image> images;
    /**
     * Stores already loaded block texture
     */
    private Map<String, WritableImage> blockImages;
    /**
     * Use to crop minecraft texture
     */
    private PixelReader blockTextureReader;
    /**
     * Store position mapping for minecraft block texture
     */
    private Map<String, Pair<Integer, Integer>> minecraftBlockNameToPos;

    /**
     * Instantiates a new Texture loader.
     */
    public TextureLoader() {
        images = new HashMap<>();
        blockImages = new HashMap<>();

        blockTextureReader = new Image(ClassLoader.getSystemResource("MinecraftTexture.png").toString()).getPixelReader();

        minecraftBlockNameToPos = new HashMap<>();

        minecraftBlockNameToPos.put("Stone", new Pair<>(1, 0));
        minecraftBlockNameToPos.put("Cobblestone", new Pair<>(0, 1));
        minecraftBlockNameToPos.put("Dirt", new Pair<>(2, 0));
        minecraftBlockNameToPos.put("Grass", new Pair<>(3, 0));
        minecraftBlockNameToPos.put("Cactus", new Pair<>(6, 4));
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
            Image image = new Image(ClassLoader.getSystemResource(resourceName + ".png").toString());
            images.put(resourceName, image);
            return image;
        }
    }

    /**
     * Gets block image.
     *
     * @param blockName the block name
     * @return the block image
     */
    public WritableImage getBlockImage(String blockName) {
        if (blockImages.containsKey(blockName)) {
            return blockImages.get(blockName);
        } else {
            Pair<Integer, Integer> pos = minecraftBlockNameToPos.get(blockName);
            WritableImage image = new WritableImage(blockTextureReader, 128 * pos.getKey(), 128 * pos.getValue(), 128, 128);
            blockImages.put(blockName, image);
            return image;
        }
    }
}
