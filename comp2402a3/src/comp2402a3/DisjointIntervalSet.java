package comp2402a3;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class implements the IntervalSet interface for storing a set of disjoint
 * intervals
 *
 * @author morin
 *
 * @param <K>
 */
public class DisjointIntervalSet<K extends Comparable<K>> implements IntervalSet<K> {

    SortedSet<Interval<K>> intervals;

    public DisjointIntervalSet() {
        intervals = new TreeSet<>();
    }

    @Override
    public boolean add(Interval<K> i) {
        if(i.getA().compareTo(i.getB()) > 0) return false;
        if(intervals.contains(i)) return false;
        return intervals.add(i);
    }

    @Override
    public void clear() {
        intervals.clear();
    }

    @Override
    public boolean contains(K x) {
    	return intervals.contains(new Interval<K>(x,x));
    }

    @Override
    public String toString() {
        return intervals.toString();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Tester.testPart1(new DisjointIntervalSet<Integer>());
    }

}
