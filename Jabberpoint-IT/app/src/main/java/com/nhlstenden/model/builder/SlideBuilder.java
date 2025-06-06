package main.java.com.nhlstenden.model.builder;

import main.java.com.nhlstenden.model.BitmapItem;
import main.java.com.nhlstenden.model.Slide;
import main.java.com.nhlstenden.model.TextItem;

/**
 * A fluent builder for creating Slide objects.
 */
public class SlideBuilder
{

    private final Slide slide;

    public SlideBuilder()
    {
        this.slide = new Slide();
    }

    public SlideBuilder withTitle(String title)
    {
        slide.setTitle(title);
        return this;
    }

    public SlideBuilder addText(int level, String content)
    {
        slide.append(new TextItem(level, content));
        return this;
    }

    public SlideBuilder addImage(int level, String imagePath)
    {
        slide.append(new BitmapItem(level, imagePath));
        return this;
    }

    public Slide build()
    {
        return slide;
    }
}
