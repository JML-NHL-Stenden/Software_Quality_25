package controller.command;

import model.Presentation;
import java.awt.Frame;

public class NewPresentationCommand implements Command {
    private final Presentation presentation;
    private final Frame parent;

    public NewPresentationCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        presentation.clear();
        parent.repaint();
    }
}
