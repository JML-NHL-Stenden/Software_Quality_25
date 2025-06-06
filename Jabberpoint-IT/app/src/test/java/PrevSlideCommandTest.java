import controller.command.PrevSlideCommand;
import model.Presentation;
import model.Slide;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrevSlideCommandTest
{
    @Test
    public void testPrevSlideReducesSlideNumber()
    {
        Presentation presentation = new Presentation();
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.setSlideNumber(1);

        PrevSlideCommand command = new PrevSlideCommand(presentation);
        command.execute();

        assertEquals(0, presentation.getSlideNumber(), "Should move back to previous slide.");
    }

    @Test
    public void testPrevSlideAtStartDoesNothing()
    {
        Presentation presentation = new Presentation();
        presentation.append(new Slide());
        presentation.setSlideNumber(0);

        PrevSlideCommand command = new PrevSlideCommand(presentation);
        command.execute();

        assertEquals(0, presentation.getSlideNumber(), "Should remain at first slide.");
    }
}
