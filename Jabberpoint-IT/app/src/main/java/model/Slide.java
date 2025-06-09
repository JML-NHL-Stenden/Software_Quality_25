package model;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>A slide. This class contains slide items (text and images).</p>
 *
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class Slide
{

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    protected String title;
    protected List<SlideItem> items;

    public Slide()
    {
        items = new ArrayList<>();
    }

    public void append(SlideItem anItem)
    {
        items.add(anItem);
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String newTitle)
    {
        this.title = newTitle;
    }

    public SlideItem getSlideItem(int number)
    {
        return items.get(number);
    }

    public List<SlideItem> getSlideItems()
    {
        return items;
    }

    public int getSize()
    {
        return items.size();
    }

    public void draw(Graphics g, Rectangle area, ImageObserver view)
    {
        float scale = getScale(area);
        int y = area.y;

        Style style = Style.getStyle(0);
        g.setFont(style.getFont(scale));
        g.setColor(style.getColor());
        g.drawString(getTitle(), area.x + (int) (style.getIndent() * scale), y);
        y += style.getLeading();

        if (items.isEmpty()) {
            g.drawString("[ EMPTY SLIDE ]", area.x + 50, y + 50);
            return;
        }

        for (SlideItem item : items) {
            Style itemStyle = Style.getStyle(item.getLevel());
            item.draw(area.x, y, scale, g, itemStyle, view);
            y += item.getBoundingBox(g, view, scale, itemStyle).height;
        }
    }

    private float getScale(Rectangle area)
    {
        return Math.min(((float) area.width) / WIDTH, ((float) area.height) / HEIGHT);
    }
}
