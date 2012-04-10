package hw2.cs236369;

import java.util.Comparator;

public class AuthorCompare implements Comparator<AuthorStats> {

    @Override
    public int compare(AuthorStats as1, AuthorStats as2) {
        return as1.name().compareTo(as2.name());
    }
}
