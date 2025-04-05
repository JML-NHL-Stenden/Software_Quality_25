package model;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

/**
 * <p>De klasse voor een Bitmap item</p>
 * <p>Bitmap items have the responsibility to draw themselves.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class BitmapItem extends SlideItem {

    private BufferedImage bufferedImage;
    private final String imageName;

    protected static final String FILE = "File ";
    protected static final String NOT_FOUND = " not found";

    // level is equal to item-level; name is the name of the file with the Image
    public BitmapItem(int level, String name) {
        super(level);
        imageName = name;
        try {
            bufferedImage = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            System.err.println(FILE + imageName + NOT_FOUND);
        }
    }

    // An empty bitmap-item
    public BitmapItem() {
        this(0, null);
    }

    // Returns the filename of the image
    public String getName() {
        return imageName;
    }

    // Computes the bounding box of the image
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer,
            float scale, Style style) {
        int width = (int) (bufferedImage.getWidth(observer) * scale);
        int height = (int) (bufferedImage.getHeight(observer) * scale);
        int x = (int) (style.indent * scale);
        int y = (int) (style.leading * scale);
        return new Rectangle(x, 0, width, y + height);
    }

    // Draws the image on the slide
    public void draw(int x, int y, float scale, Graphics g,
            Style style, ImageObserver observer) {
        if (bufferedImage == null) {
            g.setColor(Color.RED);
            g.drawString("Image not found: " + imageName, x, y);
            return;
        }
        int drawX = x + (int) (style.indent * scale);
        int drawY = y + (int) (style.leading * scale);
        int width = (int) (bufferedImage.getWidth(observer) * scale);
        int height = (int) (bufferedImage.getHeight(observer) * scale);
        g.drawImage(bufferedImage, drawX, drawY, width, height, observer);
    }

    public String toString() {
        return "BitmapItem[" + getLevel() + "," + imageName + "]";
    }
}