import model.TextItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextItemTest
{
    @Test
    public void testTextItemStoresLevelAndText()
    {
        System.out.println("Running testTextItemStoresLevelAndText: checking if text and level are stored.");
        TextItem item = new TextItem(2, "Hello");

        assertEquals(2, item.getLevel(), "Level should be 2.");
        assertEquals("Hello", item.getText(), "Text should be 'Hello'.");
        System.out.println("Result: TextItem stored text and level correctly.");
    }

    @Test
    public void testTextItemWithNullText()
    {
        System.out.println("Running testTextItemWithNullText: passing null as text.");
        TextItem item = new TextItem(1, null);

        assertEquals("", item.getText(), "TextItem should convert null text to empty string.");
        System.out.println("Result: TextItem converted null to empty string as expected.");
    }

    @Test
    public void testTextItemWithNegativeLevel()
    {
        System.out.println("Running testTextItemWithNegativeLevel: using a negative level.");
        TextItem item = new TextItem(-1, "Test");

        assertEquals(-1, item.getLevel(), "Level should be -1.");
        System.out.println("Result: TextItem stored negative level correctly.");
    }
}
