package view;

import javax.swing.JOptionPane;
import java.awt.Frame;

/**
 * De About-box voor JabberPoint.
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class AboutBox
{

    public static void show(Frame parent)
    {
        JOptionPane.showMessageDialog(parent,
            "JabberPoint is a primitive slide-show program in Java(tm).\n" +
                "It is freely copyable as long as you keep this notice and\n" +
                "the splash screen intact.\n\n" +
                "Copyright (c) 1995-1997 by Ian F. Darwin, ian@darwinsys.com.\n" +
                "Adapted by Gert Florijn (version 1.1) and\n" +
                "Sylvia Stuurman (version 1.2 and higher)\n" +
                "for the Open University of the Netherlands, 2002 -- now.\n\n" +
                "Author's version available from http://www.darwinsys.com/",
            "About JabberPoint",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
