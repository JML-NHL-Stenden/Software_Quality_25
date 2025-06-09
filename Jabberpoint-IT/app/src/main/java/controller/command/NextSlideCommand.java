package controller.command;

import model.Presentation;

/**
 * A command to navigate to the next slide in the presentation.
 */
public class NextSlideCommand implements Command
{
    private final Presentation presentation;

    /**
     * Constructs a NextSlideCommand with the given presentation.
     *
     * @param presentation the presentation to control
     */
    public NextSlideCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    /**
     * Executes the command to go to the next slide.
     */
    @Override
    public void execute()
    {
        presentation.nextSlide();
    }
}
