import accessor.XMLAccessor;
import model.Presentation;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class XMLAccessorTest
{
    @Test
    public void testSavePresentationCreatesFile() throws IOException
    {
        System.out.println("Running testSavePresentationCreatesFile: writing dummy presentation to disk.");

        XMLAccessor accessor = new XMLAccessor();
        Presentation presentation = new Presentation();
        presentation.setTitle("Test XML Save");

        String filename = "test_save.xml";
        accessor.saveFile(presentation, filename);

        File file = new File(filename);
        assertTrue(file.exists(), "File should exist after saving.");
        System.out.println("Result: XML file was saved to disk successfully.");

        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSavePresentationHandlesEmptyPresentation() throws IOException
    {
        System.out.println("Running testSavePresentationHandlesEmptyPresentation: saving a blank presentation.");

        XMLAccessor accessor = new XMLAccessor();
        Presentation presentation = new Presentation();

        String filename = "empty_pres.xml";
        accessor.saveFile(presentation, filename);

        File file = new File(filename);
        assertTrue(file.exists(), "File should exist for empty presentation.");
        System.out.println("Result: Empty presentation was saved successfully.");

        if (file.exists()) {
            file.delete();
        }
    }
}
