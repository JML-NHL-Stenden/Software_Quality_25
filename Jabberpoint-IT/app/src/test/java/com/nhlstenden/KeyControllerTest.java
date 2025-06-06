package test.java.com.nhlstenden;

import main.java.com.nhlstenden.controller.KeyController;
import main.java.com.nhlstenden.model.Presentation;
import main.java.com.nhlstenden.model.Slide;
import org.junit.jupiter.api.Test;

import java.awt.Label;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class KeyControllerTest
{

    @Test
    public void testRightArrowKeyAdvancesSlide()
    {
        System.out.println("Running testRightArrowKeyAdvancesSlide: simulating right arrow key press.");
        Presentation presentation = new Presentation();
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.setSlideNumber(0);

        KeyController controller = new KeyController(presentation);
        KeyEvent keyEvent = new KeyEvent(new Label(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');
        controller.keyPressed(keyEvent);

        assertEquals(1, presentation.getSlideNumber(), "Slide number should advance to 1.");
        System.out.println("Result: Right arrow key advanced the slide as expected.");
    }

    @Test
    public void testLeftArrowKeyGoesBack()
    {
        System.out.println("Running testLeftArrowKeyGoesBack: simulating left arrow key press.");
        Presentation presentation = new Presentation();
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.setSlideNumber(1);

        KeyController controller = new KeyController(presentation);
        KeyEvent keyEvent = new KeyEvent(new Label(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');
        controller.keyPressed(keyEvent);

        assertEquals(0, presentation.getSlideNumber(), "Slide number should decrease to 0.");
        System.out.println("Result: Left arrow key moved to the previous slide as expected.");
    }

    @Test
    public void testInvalidKeyDoesNotChangeSlide()
    {
        System.out.println("Running testInvalidKeyDoesNotChangeSlide: simulating non-arrow key press.");
        Presentation presentation = new Presentation();
        presentation.append(new Slide());
        presentation.setSlideNumber(0);

        KeyController controller = new KeyController(presentation);
        KeyEvent keyEvent = new KeyEvent(new Label(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        controller.keyPressed(keyEvent);

        assertEquals(0, presentation.getSlideNumber(), "Slide number should remain unchanged.");
        System.out.println("Result: Invalid key input did not affect slide number.");
    }
}
