package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.InputStream;

/**
 * Class that represents a bitmap item in a slide.
 */
public class BitmapItem extends SlideItem
{

    private final String imageName;
    private BufferedImage bufferedImage;

    public BitmapItem(int level, String name)
    {
        super(level);
        this.imageName = name;

        // First try loading as a resource from classpath (inside jar or build/classes)
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("images/" + imageName)) {
            if (is != null) {
                bufferedImage = ImageIO.read(is);
            } else {
                // If not found in classpath, try loading from file system path "src/images/"
                loadFromFileSystem();
            }
        } catch (IOException e) {
            System.err.println("Error reading image resource: " + imageName);
            loadFromFileSystem();
        }
    }

    private void loadFromFileSystem() {
        try {
            String path = "src/images/" + imageName;
            File file = new File(path);
            if (file.exists()) {
                bufferedImage = ImageIO.read(file);
            } else {
                System.err.println("File " + path + " not found on file system.");
            }
        } catch (IOException e) {
            System.err.println("Error reading image file from file system: " + imageName);
        }
    }

    public String getName()
    {
        return imageName;
    }

    public BufferedImage getBufferedImage()
    {
        return bufferedImage;
    }

    @Override
    public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer)
    {
        if (bufferedImage != null)
        {
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
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style)
    {
        if (bufferedImage == null)
        {
            return new Rectangle(0, 0, 0, 0);
        }

        int width = (int) (bufferedImage.getWidth(observer) * scale);
        int height = (int) (bufferedImage.getHeight(observer) * scale);
        return new Rectangle(0, 0, width, height);
    }

    @Override
    public String toString()
    {
        return "BitmapItem[" + getLevel() + "," + imageName + "]";
    }
}
