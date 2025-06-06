import accessor.XMLAccessor;
import model.Presentation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class XMLAccessorTest
{
    @Test
    public void testSavePresentationCreatesFile(@TempDir Path tempDir) throws IOException
    {
        System.out.println("Running testSavePresentationCreatesFile: writing dummy presentation to disk.");
        XMLAccessor accessor = new XMLAccessor();
        Presentation presentation = new Presentation();
        presentation.setTitle("Test XML Save");
        String filename = tempDir.resolve("test_save.xml").toString();
        accessor.saveFile(presentation, filename);
        assertTrue(new File(filename).exists(), "File should exist after saving.");
        System.out.println("Result: XML file was saved to disk successfully.");
    }

    @Test
    public void testSavePresentationHandlesEmptyPresentation(@TempDir Path tempDir) throws IOException
    {
        System.out.println("Running testSavePresentationHandlesEmptyPresentation: saving a blank presentation.");
        XMLAccessor accessor = new XMLAccessor();
        Presentation presentation = new Presentation();
        String filename = tempDir.resolve("empty_pres.xml").toString();
        accessor.saveFile(presentation, filename);
        assertTrue(new File(filename).exists(), "File should exist for empty presentation.");
        System.out.println("Result: Empty presentation was saved successfully.");
    }
}
