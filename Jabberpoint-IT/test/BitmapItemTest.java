import model.BitmapItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BitmapItemTest
{
    @Test
    public void testBitmapItemLoadsValidImage()
    {
        System.out.println("Running testBitmapItemLoadsValidImage: verifying image loading from disk.");

        BitmapItem item = new BitmapItem(1, "JabberPoint.gif");

        assertNotNull(item.getBufferedImage(), "BufferedImage should not be null for a valid image.");
        System.out.println("Result: BufferedImage was loaded successfully from JabberPoint.gif");
    }

    @Test
    public void testBitmapItemFailsGracefullyForMissingImage()
    {
        System.out.println("Running testBitmapItemFailsGracefullyForMissingImage: loading nonexistent file.");

        BitmapItem item = new BitmapItem(1, "nonexistent.png");

        assertNull(item.getBufferedImage(), "BufferedImage should be null for missing image.");
        System.out.println("Result: BufferedImage was null as expected when image was missing.");
    }

}
