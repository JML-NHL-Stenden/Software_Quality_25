package view;

import model.Presentation;
import model.Slide;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * <p>SlideViewerComponent is a graphical component that can show slides.</p>
 *
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class SlideViewerComponent extends JComponent implements PresentationObserver
{

    private static final long serialVersionUID = 227L;

    private static final Color BACKGROUND_COLOR = Color.white;
    private static final Color TEXT_COLOR = Color.black;
    private static final String FONT_NAME = "Dialog";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_SIZE = 10;
    private static final int SLIDE_NUMBER_X = 1100;
    private static final int SLIDE_NUMBER_Y = 20;

    private final JFrame frame;
    private Slide slide;
    private Font labelFont;
    private Presentation presentation;

    public SlideViewerComponent(Presentation presentation, JFrame frame)
    {
        setBackground(BACKGROUND_COLOR);
        this.presentation = presentation;
        this.frame = frame;
        this.labelFont = new Font(FONT_NAME, FONT_STYLE, FONT_SIZE);
        this.presentation.addObserver(this);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(Slide.WIDTH, Slide.HEIGHT);
    }

    @Override
    public void onSlideChanged(Presentation presentation, Slide newSlide)
    {
        this.presentation = presentation;
        this.slide = newSlide;
        repaint();
        frame.setTitle(presentation.getTitle());
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getSize().width, getSize().height);

        if (presentation == null || slide == null || presentation.getSlideNumber() < 0)
        {
            return;
        }

        g.setFont(labelFont);
        g.setColor(TEXT_COLOR);
        g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " +
                presentation.getSize(), SLIDE_NUMBER_X, SLIDE_NUMBER_Y);

        Rectangle area = new Rectangle(0, SLIDE_NUMBER_Y, getWidth(), getHeight() - SLIDE_NUMBER_Y);
        slide.draw(g, area, this);
    }
}
