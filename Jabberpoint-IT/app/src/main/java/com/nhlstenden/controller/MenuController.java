package main.java.com.nhlstenden.controller;

import main.java.com.nhlstenden.accessor.XMLAccessor;
import main.java.com.nhlstenden.controller.command.*;
import main.java.com.nhlstenden.controller.command.*;
import main.java.com.nhlstenden.accessor.Accessor;
import main.java.com.nhlstenden.model.Presentation;
import main.java.com.nhlstenden.view.AboutBox;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * <p>The controller for the menu</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar
{

    private static final long serialVersionUID = 227L;

    private final Frame parent;
    private final Presentation presentation;

    protected static final String ABOUT = "About";
    protected static final String FILE = "File";
    protected static final String EXIT = "Exit";
    protected static final String GOTO = "Go to";
    protected static final String HELP = "Help";
    protected static final String NEW = "New";
    protected static final String NEXT = "Next";
    protected static final String OPEN = "Open";
    protected static final String PAGENR = "Page number?";
    protected static final String PREV = "Prev";
    protected static final String SAVE = "Save";
    protected static final String VIEW = "View";

    protected static final String TESTFILE = "test.xml";
    protected static final String SAVEFILE = "dump.xml";

    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";

    public MenuController(Frame frame, Presentation pres)
    {
        this.parent = frame;
        this.presentation = pres;

        add(createFileMenu());
        add(createViewMenu());
        setHelpMenu(createHelpMenu());
    }

    private Menu createFileMenu()
    {
        Menu fileMenu = new Menu(FILE);

        fileMenu.add(createMenuItem(OPEN, e -> {
            presentation.clear();
            Accessor xmlAccessor = new XMLAccessor();
            try
            {
                xmlAccessor.loadFile(presentation, TESTFILE);
                presentation.setSlideNumber(-1); // force observer to refresh
                presentation.setSlideNumber(0);  // start at first actual slide
            } catch (IOException exc)
            {
                showErrorDialog(IOEX + exc, LOADERR);
            }
            parent.repaint();
        }));

        fileMenu.add(createMenuItem(NEW, e -> {
            presentation.clear();
            parent.repaint();
        }));

        fileMenu.add(createMenuItem(SAVE, new SavePresentationCommand(presentation, SAVEFILE)));

        fileMenu.addSeparator();

        fileMenu.add(createMenuItem(EXIT, new ExitCommand(presentation)));

        return fileMenu;
    }

    private Menu createViewMenu()
    {
        Menu viewMenu = new Menu(VIEW);

        viewMenu.add(createMenuItem(NEXT, new NextSlideCommand(presentation)));
        viewMenu.add(createMenuItem(PREV, new PrevSlideCommand(presentation)));

        viewMenu.add(createMenuItem(GOTO, e -> {
            String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
            try
            {
                int pageNumber = Integer.parseInt(pageNumberStr);
                new GoToSlideCommand(presentation, pageNumber - 1).execute();
            } catch (NumberFormatException ignored)
            {
            }
        }));

        return viewMenu;
    }

    private Menu createHelpMenu()
    {
        Menu helpMenu = new Menu(HELP);
        helpMenu.add(createMenuItem(ABOUT, e -> AboutBox.show(parent)));
        return helpMenu;
    }

    private MenuItem createMenuItem(String name, Command command)
    {
        MenuItem menuItem = new MenuItem(name, new MenuShortcut(name.charAt(0)));
        menuItem.addActionListener(e -> command.execute());
        return menuItem;
    }

    private MenuItem createMenuItem(String name, ActionListener listener)
    {
        MenuItem menuItem = new MenuItem(name, new MenuShortcut(name.charAt(0)));
        menuItem.addActionListener(listener);
        return menuItem;
    }

    private void showErrorDialog(String message, String title)
    {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
