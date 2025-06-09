package view;

import controller.KeyController;
import controller.MenuController;
import model.Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * <p>The application window for a slideviewcomponent</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class SlideViewerFrame extends JFrame
{

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private static final long serialVersionUID = 3227L;
    private static final String WINDOW_TITLE = "Jabberpoint 1.6 - OU";

    public SlideViewerFrame(String versionTitle, Presentation presentation)
    {
        super(versionTitle);
        SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
        initializeWindow(slideViewerComponent, presentation);
    }

    // Initializes and configures the application window
    private void initializeWindow(SlideViewerComponent component, Presentation presentation)
    {
        setTitle(WINDOW_TITLE);
        setSize(new Dimension(WIDTH, HEIGHT));
        getContentPane().add(component);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0); // Could be improved by calling a proper shutdown service
            }
        });

        addKeyListener(new KeyController(presentation)); // key controls
        setMenuBar(new MenuController(this, presentation)); // menu controller
        setVisible(true);
    }
}
