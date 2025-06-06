package test.java;

import main.java.controller.command.ExitCommand;
import main.java.model.Presentation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExitCommandTest
{
    @Test
    public void testCommandConstruction()
    {
        Presentation presentation = new Presentation();
        ExitCommand command = new ExitCommand(presentation);

        assertNotNull(command, "ExitCommand should be instantiated.");
    }
}
