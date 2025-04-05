package view;

import model.Presentation;
import model.Slide;

/**
 * Observer interface for presentation view updates.
 */
public interface PresentationObserver
{
    void onSlideChanged(Presentation presentation, Slide newSlide);
}
