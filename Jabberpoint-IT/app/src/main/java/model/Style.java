package model;

import java.awt.*;

/**
 * Style holds indentation, color, font, and leading settings.
 * Each item level in a slide corresponds directly to a style.
 *
 * @author Ian F. Darwin, ian@darwinsys.com,
 * Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class Style
{
    private static final String FONT_NAME = "Helvetica";
    private static Style[] styles;
    private int indent;
    private Color color;
    private Font font;
    private int fontSize;
    private int leading;

    public Style(int indent, Color color, int points, int leading)
    {
        this.indent = indent;
        this.color = color;
        this.fontSize = points;
        this.font = new Font(FONT_NAME, Font.BOLD, this.fontSize);
        this.leading = leading;
    }

    public static void createStyles()
    {
        styles = new Style[5];
        styles[0] = new Style(0, Color.red, 48, 20);
        styles[1] = new Style(20, Color.blue, 40, 10);
        styles[2] = new Style(50, Color.black, 36, 10);
        styles[3] = new Style(70, Color.black, 30, 10);
        styles[4] = new Style(90, Color.black, 24, 10);
    }

    public static Style getStyle(int level)
    {
        if (level >= styles.length) {
            level = styles.length - 1;
        }
        return styles[level];
    }

    public Font getFont(float scale)
    {
        return font.deriveFont(fontSize * scale);
    }

    public int getIndent()
    {
        return indent;
    }

    public Color getColor()
    {
        return color;
    }

    public int getLeading()
    {
        return leading;
    }

    @Override
    public String toString()
    {
        return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
    }
}
