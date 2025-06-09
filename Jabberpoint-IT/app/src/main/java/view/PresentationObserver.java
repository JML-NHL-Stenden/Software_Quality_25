package view;

import model.Presentation;
import model.Slide;

/**
 * An observer interface for receiving updates when the current slide changes in a presentation.
 * Implementing classes should define how to respond to slide changes.
 */
public interface PresentationObserver
{
    /**
     * Called when the slide in the given presentation changes.
     *
     * @param presentation the presentation containing the slide
     * @param newSlide     the new current slide
     */
    void onSlideChanged(Presentation presentation, Slide newSlide);
}
