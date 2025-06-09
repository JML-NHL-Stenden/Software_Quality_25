package model;

import view.PresentationObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Presentation maintains the list of slides in the presentation.
 * It also notifies observers on slide changes.
 *
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class Presentation
{
    private final List<PresentationObserver> observers = new ArrayList<>();
    private String showTitle;
    private List<Slide> showList;
    private int currentSlideNumber;

    /**
     * Constructs a new Presentation object with empty slide list.
     */
    public Presentation()
    {
        clear();
    }

    /**
     * Returns the number of slides in the presentation.
     *
     * @return slide count
     */
    public int getSize()
    {
        return showList.size();
    }

    /**
     * Returns the title of the presentation.
     *
     * @return presentation title
     */
    public String getTitle()
    {
        return showTitle;
    }

    /**
     * Sets the title of the presentation.
     *
     * @param title the new title
     */
    public void setTitle(String title)
    {
        this.showTitle = title;
        notifyObservers();
    }

    /**
     * Returns the index of the current slide.
     *
     * @return current slide index
     */
    public int getSlideNumber()
    {
        return currentSlideNumber;
    }

    /**
     * Sets the current slide by index.
     *
     * @param number the index of the slide to show
     */
    public void setSlideNumber(int number)
    {
        if (number >= 0 && number < getSize()) {
            this.currentSlideNumber = number;
            System.out.println("setSlideNumber(" + number + "), total slides: " + getSize());
            notifyObservers();
        }
    }

    /**
     * Moves to the previous slide, if available.
     */
    public void prevSlide()
    {
        if (currentSlideNumber > 0) {
            setSlideNumber(currentSlideNumber - 1);
        }
    }

    /**
     * Moves to the next slide, if available.
     */
    public void nextSlide()
    {
        int target = currentSlideNumber + 1;
        if (target < getSize()) {
            setSlideNumber(target);
        }
    }

    /**
     * Clears the presentation slides and resets the slide number.
     */
    public void clear()
    {
        showList = new ArrayList<>();
        currentSlideNumber = -1;
    }

    /**
     * Appends a new slide to the presentation.
     *
     * @param slide the Slide to add
     */
    public void append(Slide slide)
    {
        showList.add(slide);
        System.out.println("Appended slide: " + slide.getTitle() + ", total now: " + getSize());
    }

    /**
     * Retrieves a slide by its index.
     *
     * @param number index of the slide
     * @return Slide or null if out of bounds
     */
    public Slide getSlide(int number)
    {
        if (number < 0 || number >= getSize()) {
            return null;
        }
        return showList.get(number);
    }

    /**
     * Returns the currently active slide.
     *
     * @return current Slide
     */
    public Slide getCurrentSlide()
    {
        return getSlide(currentSlideNumber);
    }

    /**
     * Adds an observer to the presentation.
     *
     * @param observer the observer to add
     */
    public void addObserver(PresentationObserver observer)
    {
        observers.add(observer);
    }

    /**
     * Removes an observer from the presentation.
     *
     * @param observer the observer to remove
     */
    public void removeObserver(PresentationObserver observer)
    {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers of slide change.
     */
    private void notifyObservers()
    {
        Slide current = getCurrentSlide();
        for (PresentationObserver observer : observers) {
            observer.onSlideChanged(this, current);
        }
    }

    /**
     * Exits the program with the given status code.
     *
     * @param code exit code
     */
    public void exit(int code)
    {
        System.exit(code);
    }
}
