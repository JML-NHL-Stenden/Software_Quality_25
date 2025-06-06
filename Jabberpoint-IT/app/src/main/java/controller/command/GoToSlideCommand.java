package controller.command;

import model.Presentation;

import javax.swing.*;

public class GoToSlideCommand implements Command {
    private final Presentation presentation;
    private final Integer targetSlide;

    // Constructor for dialog-based input
    public GoToSlideCommand(Presentation presentation) {
        this(presentation, null);
    }

    // Constructor for fixed slide number
    public GoToSlideCommand(Presentation presentation, Integer targetSlide) {
        this.presentation = presentation;
        this.targetSlide = targetSlide;
    }

    @Override
    public void execute() {
        int slideNumber;

        if (targetSlide != null) {
            slideNumber = targetSlide;
        } else {
            String input = JOptionPane.showInputDialog(null, "Enter slide number:", "Go to Slide", JOptionPane.QUESTION_MESSAGE);
            if (input == null) return; // User canceled
            try {
                slideNumber = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        presentation.setSlideNumber(slideNumber);
    }
}
