package view;

import controller.KeyController;
import controller.MenuController;
import model.Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The application window for displaying a presentation slide viewer.
 * Sets up key listeners and menu controls.
 *
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class SlideViewerFrame extends JFrame
{

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private static final long serialVersionUID = 3227L;
    private static final String WINDOW_TITLE = "Jabberpoint 1.6 - OU";

    private final SlideViewerComponent slideViewerComponent;

    /**
     * Constructs the SlideViewerFrame.
     *
     * @param versionTitle the versioned title string
     * @param presentation the presentation model
     */
    public SlideViewerFrame(String versionTitle, Presentation presentation)
    {
        super(versionTitle);
        this.slideViewerComponent = new SlideViewerComponent(presentation, this);
        initializeWindow(slideViewerComponent, presentation);
    }

    /**
     * Sets up the main window with layout, controls, and listeners.
     *
     * @param component    the component used to render slides
     * @param presentation the current presentation
     */
    private void initializeWindow(SlideViewerComponent component, Presentation presentation)
    {
        setTitle(WINDOW_TITLE);
        setSize(new Dimension(WIDTH, HEIGHT));
        getContentPane().add(component);

        // Handle window close action
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                shutdown();
            }
        });

        // Input and menu handlers
        addKeyListener(new KeyController(presentation));
        setMenuBar(new MenuController(this, presentation));
        setVisible(true);
    }

    /**
     * Gracefully shuts down the application.
     * Can be expanded for resource cleanup.
     */
    private void shutdown()
    {
        System.exit(0);
    }
}
