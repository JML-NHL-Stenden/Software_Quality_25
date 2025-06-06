package test.java;

import main.java.controller.command.SavePresentationCommand;
import main.java.model.Presentation;
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
