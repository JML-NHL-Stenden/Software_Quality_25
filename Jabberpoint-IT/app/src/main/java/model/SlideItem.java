package model;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * The abstract class for an item on a slide.
 * All SlideItems support drawing functionality.
 *
 * @author Ian F. Darwin, ian@darwinsys.com,
 * Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public abstract class SlideItem
{
    private final int level;

    /**
     * Constructs a SlideItem with the given level.
     *
     * @param level the level of the item
     */
    public SlideItem(int level)
    {
        this.level = level;
    }

    /**
     * Constructs a SlideItem with default level 0.
     */
    public SlideItem()
    {
        this(0);
    }

    /**
     * Returns the level of this slide item.
     *
     * @return the level
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * Returns the bounding box of this slide item.
     *
     * @param g        the graphics context
     * @param observer the image observer
     * @param scale    the scale factor
     * @param style    the style of the item
     * @return the bounding box rectangle
     */
    public abstract Rectangle getBoundingBox(Graphics g,
                                             ImageObserver observer,
                                             float scale,
                                             Style style);

    /**
     * Draws the slide item.
     *
     * @param x        the x-coordinate
     * @param y        the y-coordinate
     * @param scale    the scale factor
     * @param g        the graphics context
     * @param style    the style to apply
     * @param observer the image observer
     */
    public abstract void draw(int x, int y, float scale,
                              Graphics g, Style style, ImageObserver observer);
}
