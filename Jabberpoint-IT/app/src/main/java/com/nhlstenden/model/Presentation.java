package main.java.com.nhlstenden.model;

import main.java.com.nhlstenden.view.PresentationObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Presentation maintains the slides in the presentation.</p>
 * <p>There is only instance of this class.</p>
 *
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class Presentation
{

    private String showTitle;
    private List<Slide> showList;
    private int currentSlideNumber;

    private final List<PresentationObserver> observers = new ArrayList<>();

    public Presentation()
    {
        clear();
    }

    public int getSize()
    {
        return showList.size();
    }

    public String getTitle()
    {
        return showTitle;
    }

    public void setTitle(String title)
    {
        this.showTitle = title;
        notifyObservers();
    }

    public int getSlideNumber()
    {
        return currentSlideNumber;
    }

    public void setSlideNumber(int number)
    {
        if (number >= 0 && number < getSize())
        {
            this.currentSlideNumber = number;
            notifyObservers();
        }
    }

    public void prevSlide()
    {
        if (currentSlideNumber > 0)
        {
            setSlideNumber(currentSlideNumber - 1);
        }
    }

    public void nextSlide()
    {
        int target = currentSlideNumber + 1;
        if (target < getSize())
        {
            setSlideNumber(target);
        }
    }

    public void clear()
    {
        showList = new ArrayList<>();
        currentSlideNumber = -1;
    }

    public void append(Slide slide)
    {
        showList.add(slide);
    }

    public Slide getSlide(int number)
    {
        if (number < 0 || number >= getSize())
        {
            return null;
        }
        return showList.get(number);
    }

    public Slide getCurrentSlide()
    {
        return getSlide(currentSlideNumber);
    }

    public void addObserver(PresentationObserver observer)
    {
        observers.add(observer);
    }

    public void removeObserver(PresentationObserver observer)
    {
        observers.remove(observer);
    }

    private void notifyObservers()
    {
        Slide current = getCurrentSlide();
        for (PresentationObserver observer : observers)
        {
            observer.onSlideChanged(this, current);
        }
    }

    public void exit(int code)
    {
        System.exit(code);
    }
}
