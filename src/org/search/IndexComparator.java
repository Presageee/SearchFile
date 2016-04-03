package org.search;

import org.search.domain.Index;

import java.util.Comparator;

/**
 * Created by LJT on 2016/4/3.
 */
public class IndexComparator implements Comparator<Index> {
    @Override
    public int compare(Index o1, Index o2) {
        double weight = (double) o1.getCount() / o1.getLength();
        double weight2 = (double) o2.getCount() / o2.getLength();
        if (weight < weight2)
            return 1;
        else if (weight == weight2)
            return 0;
        return -1;
    }
}
