package comp2402a3;
import java.util.SortedSet;
import java.util.TreeSet;
/**
 * This class implements the IntervalSet interface for storing a set of
 * intervals, which may or may not be disjoint.
 *
 * @author morin
 *
 * @param <K>
 */
public class OverlappingIntervalSet<K extends Comparable<K>> implements IntervalSet<K> {

    SortedSet<Interval<K>> intervals;

    public OverlappingIntervalSet() {
        intervals = new TreeSet<>();
    }
    private Interval<K> createInterval(K i, K j){
    	return new Interval<K>(i,j);
    }
    private SortedSet<Interval<K>> createInstance(SortedSet<Interval<K>> a){
    	return new TreeSet<Interval<K>>(a);
    }
    private K min(K i, K j){
    	return i.compareTo(j) < 0 ? i : j;
    }
    @Override	
    public boolean add(Interval<K> i) {
    	System.out.println("Adding: "+ i);
    	if(i.getA().compareTo(i.getB()) > 0) return false;
    	Interval<K> j = i;
    	if(!intervals.isEmpty()){
	    	SortedSet<Interval<K>> tail =intervals.tailSet(createInterval(i.getB(), i.getB()));
	    	SortedSet<Interval<K>> head =intervals.headSet(createInterval(i.getB(), i.getB()));
	    	
	    	System.out.println("Tail: "+ tail);
	    	System.out.println("Head: "  + head );
	    	//base cases
	    	if(i.getA().compareTo(intervals.first().getA()) <= 0 &&
	    	   i.getB().compareTo(intervals.last().getB()) >= 0){
	    		intervals.clear();
	    		System.out.println("A");
	    	}
	    	else if(tail.isEmpty()){
	    		intervals = createInstance(head);
	    		System.out.println("B");
	    	}

	    	
	    	else if(head.isEmpty()){
	    		intervals = createInstance(tail.tailSet(createInterval(i.getB(), i.getB())));
	    		System.out.println("C");
	    	}
	    	
	    	if(!tail.isEmpty()){
	    		if(tail.first().getA().equals(i.getB())){
	    			i = createInterval(min(i.getA(),tail.first().getA()), tail.first().getB());
	    			intervals.remove(tail.first());
		    		System.out.println("D");

	    		}
	    		else if(tail.first().getA().compareTo(i.getB()) < 0){
	    			i = createInterval(min(i.getA(), tail.first().getA()), tail.first().getB());
	    			intervals.remove(tail.first());
		    		System.out.println("E");

	    		}
	    	}
	    		
	    	if(!head.isEmpty()){
	    		if(head.last().getB().equals(i.getA())){
	    			i = createInterval(head.last().getA(), i.getB());
	    			intervals.remove(head.last());
		    		System.out.println("F");

	    		}
	    		else if(head.last().getB().compareTo(i.getA()) > 0){
	    			i = createInterval(head.last().getA(), i.getB());
	    			intervals.remove(head.last());
		    		System.out.println("G");

	    		}
	    	}
	    	
    	}
    	intervals.add(i);
    	System.out.println("Intervals: " + intervals);
    	return true;
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
        Tester.testPart2(new OverlappingIntervalSet<Integer>());
    }

}