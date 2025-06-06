package test.java;

import main.java.controller.MenuController;
import main.java.model.Presentation;
import org.junit.jupiter.api.Test;

import java.awt.Frame;

import static org.junit.jupiter.api.Assertions.*;

public class MenuControllerTest
{
    @Test
    public void testMenuControllerInstantiatesWithValidArguments()
    {
        System.out.println("Running testMenuControllerInstantiatesWithValidArguments: creating with dummy frame.");
        Frame frame = new Frame();
        Presentation presentation = new Presentation();

        MenuController controller = new MenuController(frame, presentation);
        assertNotNull(controller, "MenuController should be created with a frame and presentation.");
        System.out.println("Result: MenuController was created successfully.");
    }
}
