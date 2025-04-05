package accessor;

import model.Presentation;

import java.io.IOException;

/**
 * <p>Een Accessor maakt het mogelijk om gegevens voor een presentatie
 * te lezen of te schrijven.</p>
 * <p>Niet-abstracte subklassen moeten de load en de save methode implementeren.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public abstract class Accessor
{

    public static final String DEMO_NAME = "Demonstration presentation";
    public static final String DEFAULT_EXTENSION = ".xml";

    public static Accessor getDemoAccessor()
    {
        return new DemoPresentation();
    }

    public Accessor()
    {
        // Default constructor
    }

    public abstract void loadFile(Presentation presentation, String filename) throws IOException;

    public abstract void saveFile(Presentation presentation, String filename) throws IOException;
}
