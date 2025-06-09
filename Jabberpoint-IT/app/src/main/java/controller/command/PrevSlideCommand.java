package controller.command;

import model.Presentation;

/**
 * A command to navigate to the previous slide in the presentation.
 */
public class PrevSlideCommand implements Command
{
    private final Presentation presentation;

    /**
     * Constructs a PrevSlideCommand with the given presentation.
     *
     * @param presentation the presentation to control
     */
    public PrevSlideCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    /**
     * Executes the command to go to the previous slide.
     */
    @Override
    public void execute()
    {
        presentation.prevSlide();
    }
}
