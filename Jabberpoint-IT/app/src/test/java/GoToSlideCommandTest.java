package test.java;

import main.java.controller.command.GoToSlideCommand;
import main.java.model.Presentation;
import main.java.model.Slide;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoToSlideCommandTest
{

    @Test
    public void testGoToSlideSetsCorrectIndex()
    {
        Presentation presentation = new Presentation();
        presentation.append(new Slide());
        presentation.append(new Slide());

        GoToSlideCommand command = new GoToSlideCommand(presentation, 1);
        command.execute();

        assertEquals(1, presentation.getSlideNumber(), "Should jump to slide 1.");
    }

    @Test
    public void testGoToInvalidSlideDoesNothing()
    {
        Presentation presentation = new Presentation();
        presentation.append(new Slide());

        GoToSlideCommand command = new GoToSlideCommand(presentation, 5);
        command.execute();

        assertNotEquals(5, presentation.getSlideNumber(), "Invalid slide number should be ignored.");
    }
}
