package model.builder;

import model.BitmapItem;
import model.Slide;
import model.SlideItem;
import model.TextItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fluent builder for creating Slide objects.
 * Ensures proper construction and input validation before building.
 */
public class SlideBuilder
{
    private final List<SlideItem> items;
    private String title;

    /**
     * Constructs a new SlideBuilder with an empty item list.
     */
    public SlideBuilder()
    {
        this.items = new ArrayList<>();
    }

    /**
     * Sets the title of the slide.
     *
     * @param title the slide title
     * @return this builder instance
     */
    public SlideBuilder withTitle(String title)
    {
        if (title==null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Slide title cannot be null or empty.");
        }
        this.title = title.trim();
        return this;
    }

    /**
     * Adds a text item to the slide.
     *
     * @param level   the level (indentation or hierarchy) of the text
     * @param content the actual text content
     * @return this builder instance
     */
    public SlideBuilder addText(int level, String content)
    {
        if (content==null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Text content cannot be null or empty.");
        }
        items.add(new TextItem(level, content.trim()));
        return this;
    }

    /**
     * Adds an image item to the slide.
     *
     * @param level     the level of the image
     * @param imagePath the path to the image file
     * @return this builder instance
     */
    public SlideBuilder addImage(int level, String imagePath)
    {
        if (imagePath==null || imagePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Image path cannot be null or empty.");
        }
        items.add(new BitmapItem(level, imagePath.trim()));
        return this;
    }

    /**
     * Builds the Slide object from the builder state.
     *
     * @return a fully constructed Slide
     * @throws IllegalStateException if required fields are missing
     */
    public Slide build()
    {
        if (title==null || title.trim().isEmpty()) {
            throw new IllegalStateException("Slide must have a title before building.");
        }

        Slide slide = new Slide();
        slide.setTitle(title);

        for (SlideItem item : items) {
            slide.append(item);
        }

        return slide;
    }

    /**
     * Resets the builder state, allowing reuse.
     *
     * @return this builder instance, cleared and ready for new input
     */
    public SlideBuilder reset()
    {
        this.title = null;
        this.items.clear();
        return this;
    }
}
