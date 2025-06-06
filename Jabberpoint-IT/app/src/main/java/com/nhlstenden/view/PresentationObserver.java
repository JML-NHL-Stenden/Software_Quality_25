package main.java.com.nhlstenden.view;

import main.java.com.nhlstenden.model.Presentation;
import main.java.com.nhlstenden.model.Slide;

/**
 * Observer interface for presentation view updates.
 */
public interface PresentationObserver
{
    void onSlideChanged(Presentation presentation, Slide newSlide);
}
