package main.java.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>A tekst item.</p>
 * <p>A TextItem has drawingfunctionality.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class TextItem extends SlideItem
{

    private final String text;

    private static final String EMPTYTEXT = "No Text Given";

    // a textitem of level level, with the text string
    public TextItem(int level, String string)
    {
        super(level);
        text = string;
    }

    // an empty textitem
    public TextItem()
    {
        this(0, EMPTYTEXT);
    }

    // Returns the text content of this item
    public String getText()
    {
        return text == null ? "" : text;
    }

    // Creates an AttributedString using the item's style
    public AttributedString getAttributedString(Style style, float scale)
    {
        AttributedString attrStr = new AttributedString(getText());
        attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
        return attrStr;
    }

    // Computes the bounding box for this text item
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer,
            float scale, Style style)
    {
        List<TextLayout> layouts = getLayouts(g, style, scale);
        int width = 0;
        int height = (int) (style.leading * scale);
        for (TextLayout layout : layouts)
        {
            Rectangle2D bounds = layout.getBounds();
            if (bounds.getWidth() > width)
            {
                width = (int) bounds.getWidth();
            }
            if (bounds.getHeight() > 0)
            {
                height += bounds.getHeight();
            }
            height += layout.getLeading() + layout.getDescent();
        }
        return new Rectangle((int) (style.indent * scale), 0, width, height);
    }

    // Draws this text item
    public void draw(int x, int y, float scale, Graphics g,
            Style style, ImageObserver observer)
    {
        if (text == null || text.isEmpty())
        {
            return;
        }

        List<TextLayout> layouts = getLayouts(g, style, scale);
        Point pen = new Point(x + (int) (style.indent * scale),
                y + (int) (style.leading * scale));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(style.color);

        for (TextLayout layout : layouts)
        {
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += layout.getDescent();
        }
    }

    // Breaks text into styled layouts for rendering
    private List<TextLayout> getLayouts(Graphics g, Style style, float scale)
    {
        List<TextLayout> layouts = new ArrayList<>();
        AttributedString attrStr = getAttributedString(style, scale);
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
        float wrappingWidth = (Slide.WIDTH - style.indent) * scale;

        while (measurer.getPosition() < getText().length())
        {
            layouts.add(measurer.nextLayout(wrappingWidth));
        }
        return layouts;
    }

    public String toString()
    {
        return "TextItem[" + getLevel() + "," + getText() + "]";
    }
}
