package hw2.cs236369;

public class AuthorStats {
    protected String m_name;
    protected int    m_nworks;          // total number of authored content
                                         // (papers, books, etc.)

    protected double m_avgWorksPerYear; // average number of papers per year,
                                         // since first publication

    protected int    m_maxWorksPerYear; // max number of papers per year
    protected int    m_firstPubYear;    // year of first publication
    protected int    m_lastPubYear;     // year of last publication
    protected double m_avgCoauthPerWork; // average number of co-authors per
                                         // publication

    protected int    m_totalCoauth;     // total number of co-authors

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public AuthorStats(String name) {
        m_name = name;
    }

    public String name() {
        return m_name;
    }

    public void update(WorkItem newWork) {
        // [TODO]
    }
}
