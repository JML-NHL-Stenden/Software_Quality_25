package controller;

import accessor.XMLAccessor;
import controller.command.*;
import accessor.Accessor;
import model.Presentation;
import view.AboutBox;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;

public class MenuController extends MenuBar {

    private static final long serialVersionUID = 227L;
    private final Frame parent;
    private final Presentation presentation;

    public MenuController(Frame frame, Presentation presentation) {
        this.parent = frame;
        this.presentation = presentation;

        Menu fileMenu = new Menu("File");
        fileMenu.add(createMenuItem("New", e -> new NewPresentationCommand(presentation, parent).execute()));
        fileMenu.add(createMenuItem("Open", e -> openPresentation()));
        fileMenu.add(createMenuItem("Save", e -> new SavePresentationCommand(presentation).execute()));
        fileMenu.add(createMenuItem("Exit", e -> new ExitCommand().execute()));
        add(fileMenu);

        Menu viewMenu = new Menu("View");
        viewMenu.add(createMenuItem("Next", e -> new NextSlideCommand(presentation).execute()));
        viewMenu.add(createMenuItem("Prev", e -> new PrevSlideCommand(presentation).execute()));
        viewMenu.add(createMenuItem("Go to", e -> {
            String input = JOptionPane.showInputDialog("Enter slide number:");
            if (input != null) {
                try {
                    int slideNumber = Integer.parseInt(input);
                    new GoToSlideCommand(presentation, slideNumber).execute();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(parent, "Invalid number");
                }
            }
        }));
        add(viewMenu);

        Menu helpMenu = new Menu("Help");
        helpMenu.add(createMenuItem("About", e -> AboutBox.show(parent)));
        add(helpMenu);
    }

    private void openPresentation() {
        Accessor xmlAccessor = new XMLAccessor();
        try {
            presentation.clear();
            InputStream stream = getClass().getClassLoader().getResourceAsStream("test.xml");
            if (stream == null) {
                throw new IOException("test.xml not found in classpath");
            }
            xmlAccessor.loadFile(presentation, stream);
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, "IO Error: " + exc.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private MenuItem createMenuItem(String name, ActionListener listener) {
        MenuItem item = new MenuItem(name);
        item.addActionListener(listener);
        return item;
    }
}
