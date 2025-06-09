package accessor;

import model.Presentation;
import model.Slide;
import model.builder.SlideBuilder;

import java.io.IOException;

/**
 * A hardcoded demo presentation for testing.
 */
public class DemoPresentation extends Accessor
{

    /**
     * Loads a demonstration presentation into the given {@link Presentation} object.
     *
     * @param presentation   the presentation to populate
     * @param unusedFilename a placeholder filename (ignored)
     * @throws IOException if the loading fails
     */
    @Override
    public void loadFile(Presentation presentation, String unusedFilename) throws IOException
    {
        if (presentation==null) {
            throw new IllegalArgumentException("Presentation cannot be null.");
        }

        presentation.setTitle("Demo Presentation");
        SlideBuilder builder = new SlideBuilder();

        // First slide
        Slide slide1 = builder
            .withTitle("Welcome to JabberPoint")
            .addText(1, "The Java Presentation Tool")
            .addText(2, "Copyright (c) 1996-2000: Ian Darwin")
            .addText(2, "Copyright (c) 2000-now:")
            .addText(2, "Gert Florijn and Sylvia Stuurman")
            .addText(4, "Starting JabberPoint without a filename")
            .addText(4, "shows this presentation")
            .addText(1, "Navigate:")
            .addText(3, "Goto: G or Enter")
            .addText(3, "Next slide: Up Arrow or Right Arrow")
            .addText(3, "Previous slide: Down Arrow or Left Arrow")
            .addText(3, "Quit: Q")
            .build();
        presentation.append(slide1);
        builder.reset();

        // Second slide
        Slide slide2 = builder
            .withTitle("Demonstration of levels and stijlen")
            .addText(1, "Level 1")
            .addText(2, "Level 2")
            .addText(1, "Again level 1")
            .addText(1, "Level 1 has style number 1")
            .addText(2, "Level 2 has style number 2")
            .addText(3, "This is how level 3 looks like")
            .addText(4, "And this is level 4")
            .build();
        presentation.append(slide2);
        builder.reset();

        // Third slide
        Slide slide3 = builder
            .withTitle("The third slide")
            .addText(1, "To open a new presentation,")
            .addText(2, "use File->Open from the menu.")
            .addText(1, "This is the end of the presentation.")
            .addImage(1, "JabberPoint.gif")
            .build();
        presentation.append(slide3);

        presentation.setSlideNumber(0);
    }

    /**
     * Saving is not supported for the demo presentation.
     *
     * @param presentation the presentation to save
     * @param filename     the filename (unused)
     * @throws IOException always thrown as saving is not supported
     */
    @Override
    public void saveFile(Presentation presentation, String filename) throws IOException
    {
        throw new UnsupportedOperationException("Save not supported for demo presentation.");
    }
}
