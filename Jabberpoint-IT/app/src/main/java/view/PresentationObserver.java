package main.java.view;

import main.java.model.Presentation;
import main.java.model.Slide;

/**
 * Observer interface for presentation view updates.
 */
public interface PresentationObserver
{
    void onSlideChanged(Presentation presentation, Slide newSlide);
}
