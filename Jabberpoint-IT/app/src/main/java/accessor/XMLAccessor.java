package accessor;

import model.Presentation;
import model.builder.SlideBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Loads a Presentation from an XML file.
 */
public class XMLAccessor extends Accessor
{

    /**
     * Loads a presentation from the given XML file.
     *
     * @param presentation the Presentation object to populate
     * @param filename     the XML filename or resource path
     * @throws IOException if file not found or parsing fails
     */
    @Override
    public void loadFile(Presentation presentation, String filename) throws IOException
    {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream(filename);

            // Fallback: try absolute file path
            if (input==null) {
                File file = new File(filename);
                if (!file.exists()) {
                    throw new IOException("File not found: " + filename);
                }
                input = new FileInputStream(file);
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(input);
            Element root = document.getDocumentElement();

            parsePresentation(presentation, root);
        }
        catch (Exception e) {
            throw new IOException("Error loading presentation: " + e.getMessage(), e);
        }
    }

    /**
     * Parses XML content and populates the presentation object.
     *
     * @param presentation the presentation to modify
     * @param root         the root XML element
     */
    private void parsePresentation(Presentation presentation, Element root)
    {
        presentation.setTitle(getTextContent(root, "title"));
        NodeList slideNodes = root.getElementsByTagName("slide");

        SlideBuilder builder = new SlideBuilder();

        for (int i = 0; i < slideNodes.getLength(); i++) {
            Element slideElement = (Element) slideNodes.item(i);
            String slideTitle = getTextContent(slideElement, "title");
            builder.withTitle(slideTitle);

            NodeList itemNodes = slideElement.getElementsByTagName("item");
            for (int j = 0; j < itemNodes.getLength(); j++) {
                Element item = (Element) itemNodes.item(j);
                int level = Integer.parseInt(item.getAttribute("level"));
                String kind = item.getAttribute("kind");
                String content = item.getTextContent();

                if ("text".equalsIgnoreCase(kind)) {
                    builder.addText(level, content);
                }
                else if ("image".equalsIgnoreCase(kind)) {
                    builder.addImage(level, content);
                }
            }

            var slide = builder.build();
            if (slide!=null) {
                presentation.append(slide);
            }

            builder.reset();
        }
    }

    /**
     * Retrieves text content from a tag within an XML element.
     *
     * @param parent  the parent element
     * @param tagName the tag to extract
     * @return text content or empty string if missing
     */
    private String getTextContent(Element parent, String tagName)
    {
        NodeList nodes = parent.getElementsByTagName(tagName);
        if (nodes.getLength()==0) {
            return "";
        }
        return nodes.item(0).getTextContent();
    }

    /**
     * Not implemented: saving a presentation to XML.
     *
     * @param presentation the presentation to save
     * @param filename     the output filename
     * @throws IOException always thrown
     */
    @Override
    public void saveFile(Presentation presentation, String filename) throws IOException
    {
        throw new UnsupportedOperationException("Saving XML is not implemented yet.");
    }
}
