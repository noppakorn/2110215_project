package initializer;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;


public class TextureLoader {
    private Map<String, Image> images;
    public TextureLoader() {
        images = new HashMap<>();
    }

    public Image getImage(String name) {
        if (images.containsKey(name)) {
            return images.get(name);
        } else {
            System.out.println(name+".png");
            Image image = new Image(ClassLoader.getSystemResource(name + ".png").toString());
            images.put(name, image);
            return image;
        }
    }
}
