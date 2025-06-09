import accessor.XMLAccessor;
import model.Presentation;
import model.Slide;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XMLAccessorLoadTest
{

    @Test
    public void testLoadFileWithSingleSlide() throws IOException
    {
        System.out.println("Running testLoadFileWithSingleSlide...");

        String xmlContent = """
            <presentation>
                <title>Test Presentation</title>
                <slide>
                    <title>Welcome</title>
                    <item level="1" kind="text">Hello, world!</item>
                    <item level="2" kind="image">test.png</item>
                </slide>
            </presentation>
            """;

        File tempFile = File.createTempFile("presentation", ".xml");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(xmlContent);
        }

        XMLAccessor accessor = new XMLAccessor();
        Presentation presentation = new Presentation();
        accessor.loadFile(presentation, tempFile.getAbsolutePath());

        assertEquals("Test Presentation", presentation.getTitle());
        assertEquals(1, presentation.getSize());

        Slide slide = presentation.getSlide(0);
        assertEquals("Welcome", slide.getTitle());
        assertEquals(2, slide.getSize());

        System.out.println("Test passed: XML loaded and parsed successfully.");

        tempFile.deleteOnExit();
    }
}
