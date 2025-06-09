package accessor;

import model.Presentation;
import model.Slide;
import model.BitmapItem;
import model.TextItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * XMLAccessor loads a Presentation from an XML file or stream.
 */
public class XMLAccessor extends Accessor {

    @Override
    public void loadFile(Presentation presentation, String filename) throws IOException {
        try {
            final File xmlFile = new File(filename);
            final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final Document document = builder.parse(xmlFile);
            parseDocument(presentation, document);
        } catch (Exception e) {
            throw new IOException("Error loading presentation: " + e.getMessage(), e);
        }
    }

    public void loadFile(Presentation presentation, InputStream inputStream) throws IOException {
        try {
            final DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final Document document = builder.parse(inputStream);
            parseDocument(presentation, document);
        } catch (Exception e) {
            throw new IOException("Error loading presentation from stream: " + e.getMessage(), e);
        }
    }

    private void parseDocument(Presentation presentation, Document document) {
        final Element doc = document.getDocumentElement();
        presentation.setTitle(getShowTitle(doc));

        final NodeList slides = doc.getElementsByTagName("slide");
        for (int slideIndex = 0; slideIndex < slides.getLength(); slideIndex++) {
            final Element xmlSlide = (Element) slides.item(slideIndex);
            final Slide slide = new Slide();

            slide.setTitle(getTitle(xmlSlide));

            final NodeList items = xmlSlide.getElementsByTagName("item");
            for (int itemIndex = 0; itemIndex < items.getLength(); itemIndex++) {
                final Element item = (Element) items.item(itemIndex);
                loadSlideItem(slide, item);
            }

            presentation.append(slide);
        }
    }

    private String getShowTitle(Element doc) {
        final NodeList showTitleList = doc.getElementsByTagName("showtitle");
        if (showTitleList.getLength() > 0 && showTitleList.item(0).getTextContent() != null) {
            return showTitleList.item(0).getTextContent();
        }
        return "";
    }

    private String getTitle(Element slide) {
        final NodeList titles = slide.getElementsByTagName("title");
        if (titles.getLength() > 0 && titles.item(0).getTextContent() != null) {
            return titles.item(0).getTextContent();
        }
        return "";
    }

    private void loadSlideItem(Slide slide, Element item) {
        final int level = Integer.parseInt(item.getAttribute("level"));
        final String kind = item.getAttribute("kind");
        final String content = item.getTextContent();

        if ("text".equals(kind)) {
            slide.append(new TextItem(level, content));
        } else if ("image".equals(kind)) {
            slide.append(new BitmapItem(level, content));
        } else {
            System.err.println("Unknown item kind: " + kind);
        }
    }

    @Override
    public void saveFile(Presentation presentation, String filename) throws IOException {
        throw new IOException("Saving to XML is not implemented.");
    }
}
