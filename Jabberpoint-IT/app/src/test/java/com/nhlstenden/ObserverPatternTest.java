package test.java.com.nhlstenden;

import main.java.com.nhlstenden.model.Presentation;
import main.java.com.nhlstenden.model.Slide;
import main.java.com.nhlstenden.view.PresentationObserver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObserverPatternTest
{
    private static class MockObserver implements PresentationObserver
    {
        boolean wasNotified = false;
        Slide lastSlide = null;

        @Override
        public void onSlideChanged(Presentation presentation, Slide newSlide)
        {
            wasNotified = true;
            lastSlide = newSlide;
        }
    }

    @Test
    public void testObserverReceivesSlideChangeNotification()
    {
        System.out.println("Running testObserverReceivesSlideChangeNotification: adding observer and changing slide.");
        Presentation presentation = new Presentation();
        Slide slide = new Slide();
        presentation.append(slide);

        MockObserver observer = new MockObserver();
        presentation.addObserver(observer);

        presentation.setSlideNumber(0);

        assertTrue(observer.wasNotified, "Observer should be notified.");
        assertEquals(slide, observer.lastSlide, "Observer should receive the correct slide.");
        System.out.println("Result: Observer was notified with correct slide.");
    }

    @Test
    public void testRemovedObserverDoesNotGetNotification()
    {
        System.out.println("Running testRemovedObserverDoesNotGetNotification: removing observer before change.");
        Presentation presentation = new Presentation();
        Slide slide = new Slide();
        presentation.append(slide);

        MockObserver observer = new MockObserver();
        presentation.addObserver(observer);
        presentation.removeObserver(observer);

        presentation.setSlideNumber(0);

        assertFalse(observer.wasNotified, "Removed observer should not be notified.");
        System.out.println("Result: Observer was not notified after removal.");
    }
}
