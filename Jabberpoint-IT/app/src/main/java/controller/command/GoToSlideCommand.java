package controller.command;

import model.Presentation;

import javax.swing.*;

/**
 * A command that allows the user to navigate to a specific slide.
 */
public class GoToSlideCommand implements Command
{
    private final Presentation presentation;
    private final Integer targetSlide;

    /**
     * Constructor for dialog-based slide navigation.
     *
     * @param presentation the presentation to modify
     */
    public GoToSlideCommand(Presentation presentation)
    {
        this(presentation, null);
    }

    /**
     * Constructor for fixed slide navigation.
     *
     * @param presentation the presentation to modify
     * @param targetSlide  the slide number to go to
     */
    public GoToSlideCommand(Presentation presentation, Integer targetSlide)
    {
        this.presentation = presentation;
        this.targetSlide = targetSlide;
    }

    /**
     * Executes the command to go to a specific slide.
     * If no slide is preset, prompts the user for input.
     */
    @Override
    public void execute()
    {
        int slideNumber;

        if (targetSlide!=null) {
            slideNumber = targetSlide;
        }
        else {
            String input = JOptionPane.showInputDialog(
                null,
                "Enter slide number:",
                "Go to Slide",
                JOptionPane.QUESTION_MESSAGE
            );

            if (input==null) {
                return; // User canceled
            }

            try {
                slideNumber = Integer.parseInt(input.trim());
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Invalid number!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
        }

        presentation.setSlideNumber(slideNumber);
    }
}
