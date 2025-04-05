package controller;

import controller.command.*;
import model.Presentation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/**
 * <p>This is the KeyController (KeyListener)</p>
 *
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class KeyController extends KeyAdapter
{

    private final Presentation presentation;

    public KeyController(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        Command command = null;

        switch (keyEvent.getKeyCode())
        {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_ENTER:
            case '+':
                command = new NextSlideCommand(presentation);
                break;

            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case '-':
                command = new PrevSlideCommand(presentation);
                break;

            case 'q':
            case 'Q':
                command = new ExitCommand(presentation);
                break;
        }

        if (command != null)
        {
            command.execute();
        }
    }
}
