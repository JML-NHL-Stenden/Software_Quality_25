package model.builder;

import model.BitmapItem;
import model.Slide;
import model.TextItem;

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
