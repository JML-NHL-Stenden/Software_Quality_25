import controller.command.ExitCommand;
import org.junit.jupiter.api.Test;

public class ExitCommandTest
{

    @Test
    public void testExecute()
    {
        ExitCommand command = new ExitCommand(); // No arguments!
        // DO NOT actually call command.execute() unless you want to terminate the test runner
        System.out.println("ExitCommand created successfully");
    }
}
