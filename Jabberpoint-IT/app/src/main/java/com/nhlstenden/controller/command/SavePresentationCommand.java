package main.java.com.nhlstenden.controller.command;

import main.java.com.nhlstenden.accessor.XMLAccessor;
import main.java.com.nhlstenden.model.Presentation;

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
