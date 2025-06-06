import accessor.DemoPresentation;
import model.Presentation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoPresentationTest
{
    @Test
    public void testLoadDefaultPresentation() throws Exception
    {
        Presentation presentation = new Presentation();
        DemoPresentation demo = new DemoPresentation();

        demo.loadFile(presentation, "");

        assertNotNull(presentation.getTitle(), "Presentation title should be set.");
        assertTrue(presentation.getSize() > 0, "Presentation should have at least one slide.");
    }
}
