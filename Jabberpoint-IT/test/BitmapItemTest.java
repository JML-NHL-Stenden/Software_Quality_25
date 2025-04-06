import model.BitmapItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitmapItemTest
{
    @Test
    public void testBitmapItemLoadsValidImage()
    {
        System.out.println("Running testBitmapItemLoadsValidImage: verifying image loading from disk.");

        BitmapItem item = new BitmapItem(1, "Jabberpoint.gif");

        assertNotNull(item.getBufferedImage(), "BufferedImage should not be null for a valid image.");
        System.out.println("Result: BufferedImage was loaded successfully from Jabberpoint.gif");
    }

    @Test
    public void testBitmapItemFailsGracefullyForMissingImage()
    {
        System.out.println("Running testBitmapItemFailsGracefullyForMissingImage: loading nonexistent file.");

        BitmapItem item = new BitmapItem(1, "nonexistent.png");

        assertNull(item.getBufferedImage(), "BufferedImage should be null for missing image.");
        System.out.println("Result: BufferedImage was null as expected when image was missing.");
    }

    @Test
    public void testBitmapItemProperties()
    {
        System.out.println("Running testBitmapItemProperties: storing and retrieving properties.");

        BitmapItem item = new BitmapItem(2, "image.png"); // test (no actual img yet)

        assertEquals(2, item.getLevel(), "Level should be stored correctly.");
        assertEquals("image.png", item.getName(), "Image name should be stored correctly.");
        System.out.println("Result: BitmapItem stored and returned level and image name correctly.");
    }
}
