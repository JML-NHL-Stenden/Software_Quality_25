package controller.command;

import model.Presentation;

public class PrevSlideCommand implements Command
{

    private final Presentation presentation;

    public PrevSlideCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute()
    {
        presentation.prevSlide();
    }
}
