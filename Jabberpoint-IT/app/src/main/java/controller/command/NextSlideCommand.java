package controller.command;

import model.Presentation;

public class NextSlideCommand implements Command {
    private final Presentation presentation;

    public NextSlideCommand(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        presentation.nextSlide();
    }
}
