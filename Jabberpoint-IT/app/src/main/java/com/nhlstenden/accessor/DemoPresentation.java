package main.java.com.nhlstenden.accessor;

import main.java.com.nhlstenden.model.Presentation;
import main.java.com.nhlstenden.model.Slide;
import main.java.com.nhlstenden.model.builder.SlideBuilder;

import java.io.IOException;

/**
 * A built in demo-presentation
 *
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class DemoPresentation extends Accessor
{

    @Override
    public void loadFile(Presentation presentation, String unusedFilename) throws IOException
    {
        presentation.setTitle("Demo Presentation");

        Slide intro = new SlideBuilder()
                .withTitle("JabberPoint")
                .addText(1, "The Java Presentation Tool")
                .addText(2, "Copyright (c) 1996-2000: Ian Darwin")
                .addText(2, "Copyright (c) 2000-now:")
                .addText(2, "Gert Florijn and Sylvia Stuurman")
                .addText(4, "Starting JabberPoint without a filename")
                .addText(4, "shows this presentation")
                .addText(1, "Navigate:")
                .addText(3, "Next slide: PgDn or Enter")
                .addText(3, "Previous slide: PgUp or up-arrow")
                .addText(3, "Quit: q or Q")
                .build();
        presentation.append(intro);

        Slide levels = new SlideBuilder()
                .withTitle("Demonstration of levels and stijlen")
                .addText(1, "Level 1")
                .addText(2, "Level 2")
                .addText(1, "Again level 1")
                .addText(1, "Level 1 has style number 1")
                .addText(2, "Level 2 has style number 2")
                .addText(3, "This is how level 3 looks like")
                .addText(4, "And this is level 4")
                .build();
        presentation.append(levels);

        Slide finalSlide = new SlideBuilder()
                .withTitle("The third slide")
                .addText(1, "To open a new presentation,")
                .addText(2, "use File->Open from the menu.")
                .addText(1, " ")
                .addText(1, "This is the end of the presentation.")
                .addImage(1, "JabberPoint.gif") // Changed from JabberPoint.jpg to JabberPoint.gif
                .build();
        presentation.append(finalSlide);

        presentation.setSlideNumber(0); // ✅ called last
    }

    @Override
    public void saveFile(Presentation presentation, String unusedFilename)
    {
        throw new IllegalStateException("Save As->Demo! called");
    }
}
