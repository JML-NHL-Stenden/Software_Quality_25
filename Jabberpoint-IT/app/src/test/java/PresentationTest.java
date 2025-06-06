package test.java;

import main.java.model.Presentation;
import main.java.model.Slide;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PresentationTest
{
    @Test
    public void testAppendSlideAndRetrieve()
    {
        System.out.println("Running testAppendSlideAndRetrieve: appending and checking slide.");
        Presentation presentation = new Presentation();
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        presentation.append(slide);

        assertEquals(slide, presentation.getSlide(0), "Appended slide should be retrievable.");
        System.out.println("Result: Slide was added and retrieved successfully.");
    }

    @Test
    public void testInvalidSlideIndexReturnsNull()
    {
        System.out.println("Running testInvalidSlideIndexReturnsNull: accessing non-existent index.");
        Presentation presentation = new Presentation();
        assertNull(presentation.getSlide(5), "Should return null for out-of-bounds access.");
        System.out.println("Result: Out-of-bounds access correctly returned null.");
    }

    @Test
    public void testNextAndPreviousSlideNavigation()
    {
        System.out.println("Running testNextAndPreviousSlideNavigation: navigating through slides.");
        Presentation presentation = new Presentation();
        presentation.append(new Slide());
        presentation.append(new Slide());

        presentation.setSlideNumber(0);
        presentation.nextSlide();
        assertEquals(1, presentation.getSlideNumber(), "Should move to next slide.");

        presentation.prevSlide();
        assertEquals(0, presentation.getSlideNumber(), "Should return to previous slide.");
        System.out.println("Result: Slide navigation behaved as expected.");
    }

    @Test
    public void testClearPresentation()
    {
        System.out.println("Running testClearPresentation: clearing all slides.");
        Presentation presentation = new Presentation();
        presentation.append(new Slide());
        presentation.setSlideNumber(0);
        presentation.clear();

        assertEquals(-1, presentation.getSlideNumber(), "Slide number should reset.");
        assertEquals(0, presentation.getSize(), "All slides should be cleared.");
        System.out.println("Result: Presentation was cleared correctly.");
    }
}
