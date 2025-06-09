import model.BitmapItem;
import org.junit.jupiter.api.Test;

import java.awt.Image;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BitmapItemTest {

    @Test
    public void testBitmapItemLoadsValidImage() {
        System.out.println("Running testBitmapItemLoadsValidImage: verifying image loading from classpath.");

        BitmapItem item = new BitmapItem(1, "JabberPoint.gif");
        Image image = item.getImage();  // changed from getBufferedImage()

        if (image != null) {
            System.out.println("Image loaded successfully in test.");
        } else {
            System.err.println("Image failed to load in test.");
        }

        assertNotNull(image, "Image should not be null for a valid image.");
        System.out.println("Result: Image was loaded successfully from JabberPoint.gif");
    }

    @Test
    public void testBitmapItemFailsGracefullyForMissingImage() {
        System.out.println("Running testBitmapItemFailsGracefullyForMissingImage: loading nonexistent file.");

        BitmapItem item = new BitmapItem(1, "nonexistent.png");
        Image image = item.getImage();  // changed from getBufferedImage()

        if (image == null) {
            System.out.println("Image load correctly failed for nonexistent image.");
        } else {
            System.err.println("Unexpectedly loaded image for nonexistent path.");
        }

        assertNull(image, "Image should be null for missing image.");
        System.out.println("Result: Image was null as expected when image was missing.");
    }
}
