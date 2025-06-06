package main.java.controller.command;

import main.java.accessor.XMLAccessor;
import main.java.model.Presentation;

import javax.swing.JOptionPane;
import java.io.IOException;

public class SavePresentationCommand implements Command
{

    private final Presentation presentation;
    private final String filename;

    public SavePresentationCommand(Presentation presentation, String filename)
    {
        this.presentation = presentation;
        this.filename = filename;
    }

    @Override
    public void execute()
    {
        try
        {
            new XMLAccessor().saveFile(presentation, filename);
        } catch (IOException e)
        {
            JOptionPane.showMessageDialog(null,
                    "Could not save presentation: " + e.getMessage(),
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
