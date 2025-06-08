import model.BitmapItem;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BitmapItemTest {

    @Test
    public void testBitmapItemLoadsValidImage() {
        System.out.println("Running testBitmapItemLoadsValidImage: verifying image loading from classpath.");

        BitmapItem item = new BitmapItem(1, "JabberPoint.gif");
        BufferedImage image = item.getBufferedImage();

        if (image != null) {
            System.out.println("Image loaded successfully in test.");
        } else {
            System.err.println("Image failed to load in test.");
        }

        assertNotNull(image, "BufferedImage should not be null for a valid image.");
        System.out.println("Result: BufferedImage was loaded successfully from JabberPoint.gif");
    }

    @Test
    public void testBitmapItemFailsGracefullyForMissingImage() {
        System.out.println("Running testBitmapItemFailsGracefullyForMissingImage: loading nonexistent file.");

        BitmapItem item = new BitmapItem(1, "nonexistent.png");
        BufferedImage image = item.getBufferedImage();

        if (image == null) {
            System.out.println("Image load correctly failed for nonexistent image.");
        } else {
            System.err.println("Unexpectedly loaded image for nonexistent path.");
        }

        assertNull(image, "BufferedImage should be null for missing image.");
        System.out.println("Result: BufferedImage was null as expected when image was missing.");
    }
}
