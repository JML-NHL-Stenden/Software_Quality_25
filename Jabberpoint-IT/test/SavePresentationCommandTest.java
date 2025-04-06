import controller.command.SavePresentationCommand;
import model.Presentation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavePresentationCommandTest
{

    @Test
    public void testConstructionWithParameters()
    {
        Presentation presentation = new Presentation();
        SavePresentationCommand command = new SavePresentationCommand(presentation, "file.xml");

        assertNotNull(command, "Command should be constructed with parameters.");
    }
}
