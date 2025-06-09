package controller.command;

/**
 * A command that exits the application.
 */
public class ExitCommand implements Command
{
    /**
     * Executes the command to terminate the program.
     */
    @Override
    public void execute()
    {
        System.exit(0);
    }
}
