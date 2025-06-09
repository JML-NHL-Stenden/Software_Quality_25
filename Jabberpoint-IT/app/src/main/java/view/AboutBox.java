package view;

import javax.swing.*;
import java.awt.*;

/**
 * The About box for JabberPoint.
 * Shows copyright and author information.
 *
 * @author Ian F. Darwin
 * @author Gert Florijn
 * @author Sylvia Stuurman
 * @version 1.6 2014/05/16
 */
public class AboutBox
{

    /**
     * Displays the About dialog.
     *
     * @param parent the parent frame to center the dialog on
     */
    public static void show(Frame parent)
    {
        String message = """
            JabberPoint is a primitive slide-show program in Java(tm).
            It is freely copyable as long as you keep this notice and
            the splash screen intact.
            
            Copyright (c) 1995-1997 by Ian F. Darwin, ian@darwinsys.com.
            Adapted by Gert Florijn (version 1.1) and
            Sylvia Stuurman (version 1.2 and higher)
            for the Open University of the Netherlands, 2002 -- now.
            
            Author's version available from http://www.darwinsys.com/
            """;

        JOptionPane.showMessageDialog(
            parent,
            message,
            "About JabberPoint",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
