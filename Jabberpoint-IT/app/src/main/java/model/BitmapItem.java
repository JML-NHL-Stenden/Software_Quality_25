package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class that represents a bitmap item in a slide.
 */
public class BitmapItem extends SlideItem {

    private final String imageName;
    private BufferedImage bufferedImage;

    public BitmapItem(int level, String name) {
        super(level);
        this.imageName = name;

        boolean loaded = loadFromClasspath(name);
        if (!loaded) {
            loaded = loadFromFileSystem(name);
        }

        if (!loaded) {
            System.err.println("Failed to load image: " + name);
        }
    }

    private boolean loadFromClasspath(String resourcePath) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is != null) {
                bufferedImage = ImageIO.read(is);
                return true;
            } else {
                System.err.println("Resource not found in classpath: " + resourcePath);
            }
        } catch (IOException e) {
            System.err.println("Error reading image from classpath: " + resourcePath);
            e.printStackTrace();
        }
        return false;
    }

    private boolean loadFromFileSystem(String relativePath) {
        try {
            File file = new File("src/main/resources/" + relativePath); // fallback to resource-like path
            if (file.exists()) {
                bufferedImage = ImageIO.read(file);
                return true;
            } else {
                System.err.println("File not found on disk: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Error reading image from file system: " + relativePath);
            e.printStackTrace();
        }
        return false;
    }

    public String getName() {
        return imageName;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    @Override
    public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
        if (bufferedImage != null) {
            g.drawImage(
                bufferedImage,
                x,
                y,
                (int) (bufferedImage.getWidth() * scale),
                (int) (bufferedImage.getHeight() * scale),
                observer
            );
        }
    }

    @Override
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
        if (bufferedImage == null) {
            return new Rectangle(0, 0, 0, 0);
        }

        int width = (int) (bufferedImage.getWidth(observer) * scale);
        int height = (int) (bufferedImage.getHeight(observer) * scale);
        return new Rectangle(0, 0, width, height);
    }

    @Override
    public String toString() {
        return "BitmapItem[" + getLevel() + "," + imageName + "]";
    }
}
