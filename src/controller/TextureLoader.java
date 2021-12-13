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
    private Map<String, Image> images;
    private Map<String, WritableImage> blockImages;
    private PixelReader blockTextureReader;
    private Map<String, Pair<Integer, Integer>> minecraftBlockNameToPos;
    public static Image coin0 = new Image(ClassLoader.getSystemResource(  "coin0.png").toString());
    public static Image coin1 = new Image(ClassLoader.getSystemResource(  "coin1.png").toString());
    public static Image coin2 = new Image(ClassLoader.getSystemResource(  "coin2.png").toString());
    public static Image coinBox0 = new Image(ClassLoader.getSystemResource(  "coinBox0.png").toString());
    public static Image coinBox1 = new Image(ClassLoader.getSystemResource(  "coinBox1.png").toString());
    public static Image coinBox2 = new Image(ClassLoader.getSystemResource(  "coinBox2.png").toString());
    public static Image coinBox3 = new Image(ClassLoader.getSystemResource(  "coinBox3.png").toString());
    public static Image boxCoin0 = new Image(ClassLoader.getSystemResource(  "boxCoin0.png").toString());
    public static Image boxCoin1 = new Image(ClassLoader.getSystemResource(  "boxCoin1.png").toString());
    public static Image boxCoin2 = new Image(ClassLoader.getSystemResource(  "boxCoin2.png").toString());
    public static Image boxCoin3 = new Image(ClassLoader.getSystemResource(  "boxCoin3.png").toString());
    public static Image enemy1 = new Image(ClassLoader.getSystemResource("enemy1.png").toString());
    public static Image enemy2 = new Image(ClassLoader.getSystemResource("enemy2.png").toString());
    public static Image enemyDead = new Image(ClassLoader.getSystemResource("enemyDead.png").toString());

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
