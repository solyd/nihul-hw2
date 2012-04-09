package hw2.cs236369;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Pattern;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class DblpHandler extends DefaultHandler {
    // the nesting level of current element
    private int                     m_nestingLvl   = 0;
    private WorkItem                m_currWorkItem;
    private String                  m_currEleType;

    // we look for authors whose name matches this regex
    private String                  m_authorRegex;
    private Pattern                 m_pattern;
    private Collection<AuthorStats> m_authorsStats = new HashSet<AuthorStats>();

    private final String            AUTHOR         = "author";
    private final String            YEAR           = "year";

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public DblpHandler(String authorRegex) {
        m_authorRegex = authorRegex;
        Pattern m_pattern = Pattern.compile(m_authorRegex);
    }

    private void processWorkItem() {
        for (String author : m_currWorkItem.getAuthors()) {
            if (!m_pattern.matcher(author).matches())
                continue;

            boolean authStatExists = false;
            for (AuthorStats authStat : m_authorsStats) {
                if (!authStat.name().equals(author))
                    continue;
                authStat.update(m_currWorkItem);
                authStatExists = true;
                break;
            }
            if (!authStatExists) {
                AuthorStats as = new AuthorStats(author);
                as.update(m_currWorkItem);
                m_authorsStats.add(as);
            }
        }
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void startDocument() throws SAXException {
        // [TODO]
    }

    @Override
    public void endDocument() throws SAXException {
        // [TODO]
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // we are starting to process a new major element (like an article or a
        // book)
        m_nestingLvl++;
        if (m_nestingLvl == 2) {
            m_currWorkItem = new WorkItem();
            return;
        }

        if (qName.equals(AUTHOR) || qName.equals(YEAR))
            m_currEleType = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        m_nestingLvl--;
        m_currEleType = null;
        if (m_nestingLvl == 1)
            processWorkItem();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (m_currEleType == null)
            return;

        String str = new String(ch, start, length);
        switch (m_currEleType) {
        case AUTHOR:
            m_currWorkItem.addAuthor(str);
            break;
        case YEAR:
            m_currWorkItem.setYear(Integer.parseInt(str));
            break;
        }
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void error(SAXParseException e) throws SAXException {
        // TODO Auto-generated method stub
        super.error(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        // TODO Auto-generated method stub
        super.fatalError(e);
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        // TODO Auto-generated method stub
        super.warning(e);
    }

}
