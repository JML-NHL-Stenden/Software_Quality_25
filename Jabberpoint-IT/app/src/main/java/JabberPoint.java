package main.java;

import main.java.accessor.Accessor;
import main.java.accessor.XMLAccessor;
import main.java.model.Presentation;
import main.java.view.SlideViewerFrame;
import main.java.model.Style;

import javax.swing.JOptionPane;
import java.io.IOException;

/**
 * JabberPoint Main Programma
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class JabberPoint
{

    private static final String IO_ERROR = "IO Error: ";
    private static final String JABBERPOINT_ERROR = "Jabberpoint Error ";
    private static final String VERSION = "Jabberpoint 1.6 - OU version";

    /**
     * Het Main Programma
     */
    public static void main(String[] args)
    {
        Style.createStyles();
        Presentation presentation = new Presentation();
        new SlideViewerFrame(VERSION, presentation);

        try
        {
            if (args.length == 0)
            {
                // Show demo presentation
                Accessor.getDemoAccessor().loadFile(presentation, "");
            }
            else
            {
                new XMLAccessor().loadFile(presentation, args[0]);
            }
            presentation.setSlideNumber(0);
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(
                    null,
                    IO_ERROR + ex,
                    JABBERPOINT_ERROR,
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
