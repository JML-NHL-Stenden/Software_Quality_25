package main;

import accessor.Accessor;
import accessor.XMLAccessor;
import model.Presentation;
import model.Style;
import view.SlideViewerFrame;

import javax.swing.*;
import java.io.IOException;

/**
 * JabberPoint Main Program
 * <p>
 * This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.
 * </p>
 *
 * @author Ian F. Darwin
 * @author Gert Florijn
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class JabberPoint
{
    private static final String IO_ERROR = "IO Error: ";
    private static final String JABBERPOINT_ERROR = "Jabberpoint Error";
    private static final String VERSION = "Jabberpoint 1.6 - OU version";

    /**
     * The main method to start the presentation application.
     *
     * @param args optional path to an XML presentation file
     */
    public static void main(String[] args)
    {
        Style.createStyles();
        Presentation presentation = new Presentation();
        new SlideViewerFrame(VERSION, presentation);

        try {
            if (args.length==0) {
                Accessor.getDemoAccessor().loadFile(presentation, "");
            }
            else {
                new XMLAccessor().loadFile(presentation, args[0]);
            }

            presentation.setSlideNumber(0);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(
                null,
                IO_ERROR + ex,
                JABBERPOINT_ERROR,
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
