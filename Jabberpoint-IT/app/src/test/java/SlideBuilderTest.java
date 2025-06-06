import model.BitmapItem;
import model.Slide;
import model.SlideItem;
import model.TextItem;
import model.builder.SlideBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SlideBuilderTest {

    @Test
    public void testBuildSlideWithTitleAndItems() {
        System.out.println("Running testBuildSlideWithTitleAndItems...");

        SlideBuilder builder = new SlideBuilder();
        Slide slide = builder
            .withTitle("Sample Slide")
            .addText(1, "This is a text item")
            .addImage(2, "image.png")
            .build();

        assertEquals("Sample Slide", slide.getTitle(), "Title should match");
        List<SlideItem> items = slide.getSlideItems();
        assertEquals(2, items.size(), "Slide should contain two items");

        assertTrue(items.get(0) instanceof TextItem, "First item should be TextItem");
        assertTrue(items.get(1) instanceof BitmapItem, "Second item should be BitmapItem");

        TextItem text = (TextItem) items.get(0);
        assertEquals("This is a text item", text.getText(), "Text content should match");

        BitmapItem image = (BitmapItem) items.get(1);
        assertEquals("image.png", image.getName(), "Image path should match");

        System.out.println("Result: Slide built with title, text, and image as expected.");
    }

    @Test
    public void testResetBuilderClearsPreviousState() {
        System.out.println("Running testResetBuilderClearsPreviousState...");

        SlideBuilder builder = new SlideBuilder();
        builder.withTitle("Old Slide").addText(1, "Old Text");
        builder.reset();

        Slide newSlide = builder.withTitle("New Slide").addText(2, "New Text").build();

        assertEquals("New Slide", newSlide.getTitle(), "Reset builder should allow new title");
        List<SlideItem> items = newSlide.getSlideItems();
        assertEquals(1, items.size(), "Only one item should remain after reset");

        TextItem text = (TextItem) items.get(0);
        assertEquals("New Text", text.getText(), "Text should match new input after reset");

        System.out.println("Result: Builder reset successfully cleared previous state.");
    }

    @Test
    public void testAddTextThrowsOnNull() {
        System.out.println("Running testAddTextThrowsOnNull...");

        SlideBuilder builder = new SlideBuilder();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.addText(1, null);
        });

        assertEquals("Text content cannot be null or empty.", exception.getMessage());
        System.out.println("Result: Exception correctly thrown for null text.");
    }

    @Test
    public void testAddTextThrowsOnEmptyString() {
        System.out.println("Running testAddTextThrowsOnEmptyString...");

        SlideBuilder builder = new SlideBuilder();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            builder.addText(2, "   "); // whitespace only
        });

        assertEquals("Text content cannot be null or empty.", exception.getMessage());
        System.out.println("Result: Exception correctly thrown for empty text.");
    }
}
