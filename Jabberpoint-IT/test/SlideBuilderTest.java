import model.BitmapItem;
import model.Slide;
import model.SlideItem;
import model.TextItem;
import model.builder.SlideBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SlideBuilderTest
{
    @Test
    public void testBuildSlideWithNoContent()
    {
        System.out.println("Running testBuildSlideWithNoContent: building an empty slide.");
        Slide slide = new SlideBuilder().build();

        assertNull(slide.getTitle(), "Title should be null.");
        assertEquals(0, slide.getSlideItems().size(), "Slide should contain no items.");

        System.out.println("Result: Slide was built with no title and no items as expected.");
    }

    @Test
    public void testBuildSlideWithTitleOnly()
    {
        System.out.println("Running testBuildSlideWithTitleOnly: building slide with title only.");
        Slide slide = new SlideBuilder().withTitle("Intro").build();

        assertEquals("Intro", slide.getTitle(), "Title should match input.");
        assertEquals(0, slide.getSlideItems().size(), "Slide should contain no items.");

        System.out.println("Result: Slide was built with title and no items as expected.");
    }

    @Test
    public void testBuildSlideWithSingleTextItem()
    {
        System.out.println("Running testBuildSlideWithSingleTextItem: adding one text item.");
        Slide slide = new SlideBuilder()
            .addText(1, "Welcome")
            .build();

        assertNull(slide.getTitle(), "Title should be null.");
        assertEquals(1, slide.getSlideItems().size(), "Slide should contain one item.");
        SlideItem item = slide.getSlideItems().get(0);
        assertTrue(item instanceof TextItem, "Item should be a TextItem.");
        assertEquals("Welcome", ((TextItem) item).getText(), "Text should match.");

        System.out.println("Result: Slide was built with one text item as expected.");
    }

    @Test
    public void testBuildSlideWithSingleImageItem()
    {
        System.out.println("Running testBuildSlideWithSingleImageItem: adding one image item.");
        Slide slide = new SlideBuilder()
            .addImage(2, "image.png")
            .build();

        assertNull(slide.getTitle(), "Title should be null.");
        assertEquals(1, slide.getSlideItems().size(), "Slide should contain one item.");
        SlideItem item = slide.getSlideItems().get(0);
        assertTrue(item instanceof BitmapItem, "Item should be a BitmapItem.");
        assertEquals("image.png", ((BitmapItem) item).getName(), "Image path should match.");

        System.out.println("Result: Slide was built with one image item as expected.");
    }

    @Test
    public void testBuildSlideWithTitleAndMultipleItems()
    {
        System.out.println("Running testBuildSlideWithTitleAndMultipleItems: building full slide.");
        Slide slide = new SlideBuilder()
            .withTitle("Overview")
            .addText(1, "Welcome to JabberPoint")
            .addImage(2, "logo.png")
            .build();

        assertEquals("Overview", slide.getTitle(), "Title should match.");
        assertEquals(2, slide.getSlideItems().size(), "Slide should contain two items.");

        System.out.println("Result: Slide was built with a title, one text, and one image item.");
    }
}
