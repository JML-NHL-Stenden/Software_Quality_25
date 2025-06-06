package test.java;

import main.java.controller.command.NextSlideCommand;
import main.java.model.Presentation;
import main.java.model.Slide;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NextSlideCommandTest
{
    @Test
    public void testNextSlideAdvancesSlideNumber()
    {
        Presentation presentation = new Presentation();
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.setSlideNumber(0);

        NextSlideCommand command = new NextSlideCommand(presentation);
        command.execute();

        assertEquals(1, presentation.getSlideNumber(), "Slide number should advance to 1.");
    }

    @Test
    public void testNextSlideAtEndDoesNothing()
    {
        Presentation presentation = new Presentation();
        presentation.append(new Slide());
        presentation.setSlideNumber(0);

        NextSlideCommand command = new NextSlideCommand(presentation);
        command.execute();

        assertEquals(0, presentation.getSlideNumber(), "Should not go past last slide.");
    }
}
