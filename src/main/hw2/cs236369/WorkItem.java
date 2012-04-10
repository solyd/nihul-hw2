package hw2.cs236369;

import java.util.Collection;
import java.util.HashSet;

public class WorkItem {
    private Collection<String> m_authors = new HashSet<String>();
    private int                m_year;

    public Collection<String> getAuthors() {
        return new HashSet<String>(m_authors);
    }

    public void addAuthor(String author) {
        m_authors.add(author);
    }

    public int year() {
        return m_year;
    }

    public void setYear(int year) {
        m_year = year;
    }

    @Override
    public String toString() {
        String res = "\t-AUTHORS-\n";
        for (String auth : m_authors)
            res += "\t" + auth + "\n";
        res += "-YEAR- = " + m_year;
        return res;
    }
}
