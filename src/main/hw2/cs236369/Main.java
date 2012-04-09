package hw2.cs236369;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// [TODO] rename the project to id1-id2
// [TODO] generate javadoc
// [TODO] add grisha's details to hw2.txt
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("invalid num. of arguments");
            System.exit(-1);
        }

        for (String arg : args) {
            System.out.println(arg);
        }
        System.out.println("-------------------------------------");

        // SAXexample(args);
        DblpHandlerRunner(args);
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Code snippets, examples
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public static void DblpHandlerRunner(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(args[0], new DblpHandler(args[1]));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void PatternExample(String[] args) {
        Pattern pattern = Pattern.compile(args[1]);
        Matcher matcher = pattern.matcher("expression");
        System.out.println(matcher.matches());
    }

    public static void SAXexample(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                /*
                 * startDocument() and endDocument() – Method called at the
                 * start and end of an XML document. startElement() and
                 * endElement() – Method called at the start and end of a
                 * document element. characters() – Method called with the text
                 * contents in between the start and end tags of an XML document
                 * element.
                 */

                @Override
                public void startDocument() throws SAXException {
                    System.out.println("start doc");
                }

                @Override
                public void endDocument() throws SAXException {
                    System.out.println("end doc");
                }

                @Override
                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) throws SAXException {
                    System.out.println("Start Element :" + qName);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    System.out.println("End Element :" + qName);
                }

                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    System.out.println(new String(ch, start, length));
                }
            };

            saxParser.parse(args[0], handler);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
