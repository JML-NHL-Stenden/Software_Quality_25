import controller.command.GoToSlideCommand;
import model.Presentation;
import model.Slide;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
