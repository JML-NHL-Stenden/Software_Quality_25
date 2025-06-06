package main.java.com.nhlstenden.controller.command;

import main.java.com.nhlstenden.model.Presentation;

public class ExitCommand implements Command
{

    private final Presentation presentation;

    public ExitCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute()
    {
        presentation.exit(0);
    }
}
