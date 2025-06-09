import controller.command.SavePresentationCommand;
import model.Presentation;
import org.junit.jupiter.api.Test;

public class SavePresentationCommandTest
{

    @Test
    public void testExecute()
    {
        Presentation presentation = new Presentation(); // Provide required arg
        SavePresentationCommand command = new SavePresentationCommand(presentation);
        command.execute(); // This should just print to console for now
    }
}
