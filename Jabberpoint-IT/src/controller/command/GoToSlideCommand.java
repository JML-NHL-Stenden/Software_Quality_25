package controller.command;

import model.Presentation;

public class GoToSlideCommand implements Command
{

    private final Presentation presentation;
    private final int targetSlide;

    public GoToSlideCommand(Presentation presentation, int targetSlide)
    {
        this.presentation = presentation;
        this.targetSlide = targetSlide;
    }

    @Override
    public void execute()
    {
        presentation.setSlideNumber(targetSlide);
    }
}
