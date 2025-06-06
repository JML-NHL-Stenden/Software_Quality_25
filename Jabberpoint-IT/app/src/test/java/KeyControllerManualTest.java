import controller.KeyController;
import model.Presentation;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyControllerManualTest {

    static class TestPresentation extends Presentation {
        public boolean nextCalled = false;
        public boolean prevCalled = false;
        public boolean gotoCalled = false;
        public int slideNumber = -1;

        @Override
        public void nextSlide() {
            nextCalled = true;
            System.out.println("nextSlide() called");
        }

        @Override
        public void prevSlide() {
            prevCalled = true;
            System.out.println("prevSlide() called");
        }

        @Override
        public void setSlideNumber(int number) {
            gotoCalled = true;
            slideNumber = number;
            System.out.println("setSlideNumber(" + number + ") called");
        }
    }

    @Test
    public void testNextSlideKey() {
        TestPresentation presentation = new TestPresentation();
        KeyController keyController = new KeyController(presentation);
        KeyEvent keyEvent = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_DOWN, ' ');
        keyController.keyPressed(keyEvent);

        assert presentation.nextCalled : "nextSlide() was not called!";
    }

    @Test
    public void testPrevSlideKey() {
        TestPresentation presentation = new TestPresentation();
        KeyController keyController = new KeyController(presentation);
        KeyEvent keyEvent = new KeyEvent(new Component() {}, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_UP, ' ');
        keyController.keyPressed(keyEvent);

        assert presentation.prevCalled : "prevSlide() was not called!";
    }
}
