package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * <p>The abstract class for an item on a slide<p>
 * <p>All SlideItems have drawingfunctionality.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public abstract class SlideItem
{

    private int level = 0; // level of the slideitem

    public SlideItem(int lev)
    {
        level = lev;
    }

    public SlideItem()
    {
        this(0);
    }

    // Give the level
    public int getLevel()
    {
        return level;
    }

    // Give the bounding box
    public abstract Rectangle getBoundingBox(Graphics g,
                                             ImageObserver observer,
                                             float scale,
                                             Style style);

    // Draw the item
    public abstract void draw(int x, int y, float scale,
                              Graphics g, Style style, ImageObserver observer);
}
