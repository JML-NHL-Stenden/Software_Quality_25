package controller.command;

/**
 * Represents a command that can be executed.
 * Implementations should define the behavior in the {@code execute()} method.
 */
@FunctionalInterface
public interface Command
{
    /**
     * Executes the command.
     */
    void execute();
}
