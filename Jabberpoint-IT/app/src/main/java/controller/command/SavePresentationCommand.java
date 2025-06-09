package controller.command;

import model.Presentation;

/**
 * A command to save the current presentation.
 */
public class SavePresentationCommand implements Command
{
    private final Presentation presentation;

    /**
     * Constructs a SavePresentationCommand with the given presentation.
     *
     * @param presentation the presentation to be saved
     */
    public SavePresentationCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    /**
     * Executes the save operation.
     */
    @Override
    public void execute()
    {
        System.out.println("Saving presentation..."); // Simulate saving logic
    }
}
