package controller.command;

import model.Presentation;

public class SavePresentationCommand implements Command
{
    private final Presentation presentation;

    public SavePresentationCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute()
    {
        System.out.println("Saving presentation..."); // simulate saving logic
    }
}
