package model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.net.URL;

/**
 * Class that represents a bitmap item in a slide.
 */
public class BitmapItem extends SlideItem {

    private final String imageName;
    private Image image;

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
        URL resourceUrl = getClass().getResource("/" + resourcePath);  // FIXED
        if (resourceUrl != null) {
            image = new ImageIcon(resourceUrl).getImage();
            return true;
        } else {
            System.err.println("Resource not found in classpath: " + resourcePath);
            return false;
        }
    }

    private boolean loadFromFileSystem(String relativePath) {
        File file = new File("src/main/resources/" + relativePath);
        if (file.exists()) {
            image = new ImageIcon(file.getAbsolutePath()).getImage();
            return true;
        } else {
            System.err.println("File not found on disk: " + file.getAbsolutePath());
            return false;
        }
    }

    public String getName() {
        return imageName;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
        if (image != null) {
            int width = (int) (image.getWidth(observer) * scale);
            int height = (int) (image.getHeight(observer) * scale);
            g.drawImage(image, x, y, width, height, observer);
        }
    }

    @Override
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
        if (image == null) {
            return new Rectangle(0, 0, 0, 0);
        }
        int width = (int) (image.getWidth(observer) * scale);
        int height = (int) (image.getHeight(observer) * scale);
        return new Rectangle(0, 0, width, height);
    }

    @Override
    public String toString() {
        return "BitmapItem[" + getLevel() + "," + imageName + "]";
    }
}
