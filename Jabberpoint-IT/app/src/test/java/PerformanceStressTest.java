import model.Presentation;
import model.Slide;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the ability of the Presentation class to handle 10 slides.
 */
public class PerformanceStressTest {

    @Test
    public void testTenSlides() {
        Presentation presentation = new Presentation();
        int slideCount = 10;

        for (int i = 0; i < slideCount; i++) {
            presentation.append(new Slide());
        }

        // Assertions
        assertEquals(slideCount, presentation.getSize(), "Should store 10 slides.");
        presentation.setSlideNumber(9);
        assertEquals(9, presentation.getSlideNumber(), "Should access the last slide.");
    }
}
