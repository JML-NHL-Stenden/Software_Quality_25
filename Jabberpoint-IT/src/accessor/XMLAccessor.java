package accessor;

import model.BitmapItem;
import model.Presentation;
import model.Slide;
import model.SlideItem;
import model.TextItem;
import model.builder.SlideBuilder;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.List;

/**
 * XMLAccessor, reads and writes XML files
 *
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class XMLAccessor extends Accessor
{

    protected static final String DEFAULT_API_TO_USE = "dom";

    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";

    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";

    private String getTitle(Element element, String tagName)
    {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();
    }

    @Override
    public void loadFile(Presentation presentation, String filename) throws IOException
    {
        try
        {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            Element doc = document.getDocumentElement();

            presentation.setTitle(getTitle(doc, SHOWTITLE));

            NodeList slides = doc.getElementsByTagName(SLIDE);
            for (int slideNumber = 0; slideNumber < slides.getLength(); slideNumber++)
            {
                Element xmlSlide = (Element) slides.item(slideNumber);
                SlideBuilder slideBuilder = new SlideBuilder()
                        .withTitle(getTitle(xmlSlide, SLIDETITLE));

                NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
                for (int itemNumber = 0; itemNumber < slideItems.getLength(); itemNumber++)
                {
                    Element item = (Element) slideItems.item(itemNumber);
                    appendSlideItem(slideBuilder, item);
                }

                Slide slide = slideBuilder.build();

                if (slide.getSlideItems().isEmpty())
                {
                    System.err.println("Warning: Slide '" + slide.getTitle() + "' has no content.");
                }

                presentation.append(slide);
            }

        } catch (IOException | SAXException | ParserConfigurationException e)
        {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }

    private void appendSlideItem(SlideBuilder builder, Element item)
    {
        int level = 1;
        NamedNodeMap attributes = item.getAttributes();

        String levelText = attributes.getNamedItem(LEVEL).getTextContent();
        if (levelText != null)
        {
            try
            {
                level = Integer.parseInt(levelText);
            } catch (NumberFormatException x)
            {
                System.err.println(NFE);
            }
        }

        String type = attributes.getNamedItem(KIND).getTextContent();
        String content = item.getTextContent();

        if (TEXT.equals(type))
        {
            builder.addText(level, content);
        }
        else if (IMAGE.equals(type))
        {
            builder.addImage(level, content);
        }
        else
        {
            System.err.println(UNKNOWNTYPE);
        }
    }

    @Override
    public void saveFile(Presentation presentation, String filename) throws IOException
    {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename)))
        {
            out.println("<?xml version=\"1.0\"?>");
            out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
            out.println("<presentation>");
            out.print("<showtitle>");
            out.print(presentation.getTitle());
            out.println("</showtitle>");

            for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++)
            {
                Slide slide = presentation.getSlide(slideNumber);
                out.println("<slide>");
                out.println("<title>" + slide.getTitle() + "</title>");

                List<SlideItem> slideItems = slide.getSlideItems();
                for (SlideItem slideItem : slideItems)
                {
                    out.print("<item kind=");
                    if (slideItem instanceof TextItem)
                    {
                        out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
                        out.print(((TextItem) slideItem).getText());
                    }
                    else if (slideItem instanceof BitmapItem)
                    {
                        out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
                        out.print(((BitmapItem) slideItem).getName());
                    }
                    else
                    {
                        continue;
                    }
                    out.println("</item>");
                }

                out.println("</slide>");
            }

            out.println("</presentation>");
        }
    }
}
