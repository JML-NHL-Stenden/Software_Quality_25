package model;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

/**
 * A text item on a slide.
 * TextItem has functionality to draw and measure text content.
 *
 * @author Ian F. Darwin
 * @author Gert Florijn
 * @author Sylvia Stuurman
 * @version 1.6 2014/05/16
 */
public class TextItem extends SlideItem
{
    private static final String EMPTY_TEXT = "No Text Given";
    private final String text;

    /**
     * Constructs a TextItem with a specified level and text.
     *
     * @param level the level of indentation
     * @param text  the content text
     */
    public TextItem(int level, String text)
    {
        super(level);
        this.text = text;
    }

    /**
     * Constructs an empty TextItem.
     */
    public TextItem()
    {
        this(0, EMPTY_TEXT);
    }

    /**
     * Returns the text of this item.
     *
     * @return the text string
     */
    public String getText()
    {
        return text==null ? "":text;
    }

    /**
     * Returns the attributed string for rendering.
     *
     * @param style the style to apply
     * @param scale the scale factor
     * @return the attributed string
     */
    public AttributedString getAttributedString(Style style, float scale)
    {
        AttributedString attrStr = new AttributedString(getText());
        attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
        return attrStr;
    }

    /**
     * Computes the bounding box of this item for layout.
     *
     * @param g        the graphics context
     * @param observer the image observer
     * @param scale    the scale factor
     * @param style    the item style
     * @return the bounding rectangle
     */
    @Override
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style)
    {
        List<TextLayout> layouts = getLayouts(g, style, scale);
        int width = 0;
        int height = (int) (style.getLeading() * scale);

        for (TextLayout layout : layouts) {
            Rectangle2D bounds = layout.getBounds();
            width = Math.max(width, (int) bounds.getWidth());
            if (bounds.getHeight() > 0) {
                height += bounds.getHeight();
            }
            height += layout.getLeading() + layout.getDescent();
        }

        return new Rectangle((int) (style.getIndent() * scale), 0, width, height);
    }

    /**
     * Draws the text item at a given location.
     *
     * @param x        the x-coordinate
     * @param y        the y-coordinate
     * @param scale    the scale factor
     * @param g        the graphics context
     * @param style    the style to apply
     * @param observer the image observer
     */
    @Override
    public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer)
    {
        if (text==null || text.isEmpty()) {
            return;
        }

        List<TextLayout> layouts = getLayouts(g, style, scale);
        Point pen = new Point(x + (int) (style.getIndent() * scale), y + (int) (style.getLeading() * scale));

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(style.getColor());

        for (TextLayout layout : layouts) {
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += layout.getDescent();
        }
    }

    /**
     * Converts text to a list of layout objects for wrapping and rendering.
     *
     * @param g     the graphics context
     * @param style the item style
     * @param scale the scale factor
     * @return list of text layouts
     */
    private List<TextLayout> getLayouts(Graphics g, Style style, float scale)
    {
        List<TextLayout> layouts = new ArrayList<>();
        AttributedString attrStr = getAttributedString(style, scale);
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
        float wrappingWidth = (Slide.WIDTH - style.getIndent()) * scale;

        while (measurer.getPosition() < getText().length()) {
            layouts.add(measurer.nextLayout(wrappingWidth));
        }

        return layouts;
    }

    /**
     * Returns a string representation for debugging.
     *
     * @return formatted string with level and text
     */
    @Override
    public String toString()
    {
        return "TextItem[" + getLevel() + "," + getText() + "]";
    }
}
