package comp2402a3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is a dumb implementation of an IntervalSet.
 *
 * @author morin
 *
 * @param <T>
 */
public class DumbIntervalSet<T extends Comparable<T>> implements IntervalSet<T> {

    /**
     * Maps intervals onto values
     */
    protected List<Interval<T>> intervals;

    public DumbIntervalSet() {
        intervals = new ArrayList<>();
    }

    @Override
    public boolean add(Interval<T> i) {
        return intervals.add(i);
    }

    @Override
    public boolean contains(T k) {
        Iterator<Interval<T>> it = intervals.iterator();
        while (it.hasNext()) {
            Interval<T> i = it.next();
            if (i.contains(k)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        intervals.clear();
    }

    @Override
    public String toString() {
        return intervals.toString();
    }
}
