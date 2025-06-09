package controller;

import controller.command.*;
import model.Presentation;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter
{
    private final CommandInvoker invoker = new CommandInvoker();

    public KeyController(Presentation presentation)
    {
        // Slide navigation
        invoker.bind(KeyEvent.VK_PAGE_DOWN, new NextSlideCommand(presentation));
        invoker.bind(KeyEvent.VK_RIGHT, new NextSlideCommand(presentation));
        invoker.bind(KeyEvent.VK_DOWN, new NextSlideCommand(presentation));

        invoker.bind(KeyEvent.VK_PAGE_UP, new PrevSlideCommand(presentation));
        invoker.bind(KeyEvent.VK_LEFT, new PrevSlideCommand(presentation));
        invoker.bind(KeyEvent.VK_UP, new PrevSlideCommand(presentation));

        // Jump to slide (dynamic input)
        invoker.bind(KeyEvent.VK_G, new GoToSlideCommand(presentation));
        invoker.bind(KeyEvent.VK_ENTER, new GoToSlideCommand(presentation));

        // Save
        invoker.bind(KeyEvent.VK_S, new SavePresentationCommand(presentation));

        // Exit
        invoker.bind(KeyEvent.VK_ESCAPE, new ExitCommand());
        invoker.bind(KeyEvent.VK_Q, new ExitCommand());
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        System.out.println("Key pressed: " + event.getKeyCode());
        invoker.execute(event.getKeyCode());
    }
}
