package hw2.cs236369;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class AuthorStats {
    private String                m_name;
    // average number of papers per year, since first publication
    private double                m_avgWorksPerYear;
    // year of first publication
    private int                   m_firstYear     = -1;
    // year of last publication
    private int                   m_lastYear      = -1;

    private Collection<String>    m_coauths       = new HashSet<String>();
    private Collection<WorkItem>  m_works         = new HashSet<WorkItem>();
    // key - year, value - number of works published for that year
    private Map<Integer, Integer> m_nworksPerYear = new HashMap<Integer, Integer>();

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public AuthorStats(String name) {
        m_name = name;
    }

    public void update(WorkItem newWork) {
        if (newWork.year() == -1)
            return;

        m_works.add(newWork);

        if (newWork.year() != -1) {
            if (newWork.year() > m_lastYear || m_lastYear == -1)
                m_lastYear = newWork.year();
            if (newWork.year() < m_firstYear || m_firstYear == -1)
                m_firstYear = newWork.year();
        }

        m_avgWorksPerYear = (m_works.size() * 1.0) / (m_lastYear - m_firstYear + 1);

        if (!m_nworksPerYear.containsKey(newWork.year()))
            m_nworksPerYear.put(newWork.year(), 0);
        int prevValue = m_nworksPerYear.get(newWork.year());
        m_nworksPerYear.put(newWork.year(), prevValue + 1);

        for (String auth : newWork.getAuthors())
            if (!auth.equals(m_name))
                m_coauths.add(auth);
    }

    public String name() {
        return m_name;
    }

    public int totalWorks() {
        return m_works.size();
    }

    public double avgWorksPerYear() {
        return m_avgWorksPerYear;
    }

    public int maxWorksPerYear() {
        int max = 0;
        for (Integer nworks : m_nworksPerYear.values())
            if (nworks > max)
                max = nworks;
        return max;
    }

    public int firstYear() {
        return m_firstYear;
    }

    public int lastYear() {
        return m_lastYear;
    }

    public double avgCoauthorsPerWork() {
        return (m_coauths.size() * 1.0) / m_works.size();
    }

    public int totalCoauthors() {
        return m_coauths.size();
    }

    @Override
    public String toString() {
        return String.format("%s %d %.1f %d %d %d %.1f %d", name(), totalWorks(),
                             avgWorksPerYear(), maxWorksPerYear(), firstYear(),
                             lastYear(), avgCoauthorsPerWork(), totalCoauthors());

    }
}
