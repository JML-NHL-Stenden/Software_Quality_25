package test.java;

import main.java.model.Presentation;
import main.java.model.Slide;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PerformanceStressTest
{
    @Test
    public void testLargeSlideCount()
    {
        Presentation presentation = new Presentation();
        int slideCount = 1000;

        for (int i = 0; i < slideCount; i++)
        {
            presentation.append(new Slide());
        }

        assertEquals(slideCount, presentation.getSize(), "Should store all slides.");
        presentation.setSlideNumber(999);
        assertEquals(999, presentation.getSlideNumber(), "Should access the last slide.");
    }
}
