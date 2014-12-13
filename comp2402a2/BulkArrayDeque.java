package comp2402a2;

import java.util.Collection;


public class BulkArrayDeque<T> extends ArrayDeque<T> {
	public BulkArrayDeque(Class<T> clazz) {
		super(clazz);
	}
	
	/**
	 * Add all the elements of c to this array deque, starting
	 * at position i
	 * @param i
	 * @param c
	 */
	public boolean addAll(int i, Collection<? extends T> c) {
		// this implementation is too slow 
		//  make it run in O(c.size()+n-i) time.
		
		T[] b = f.newArray(this.size() + c.size());
		int t = i;
		for(int j = 0 ; j < i ; j++) //for all elements before collection
			b[j] = a[j];
		for(T j : c)
			b[i++] = j;
		for(int j = i ; j < b.length ; j++)
			b[j] = a[t++];
		super.n = b.length;
		super.a = b;
		return true;
	}

	
    public static void main(String[] args) {
        if (!Tester.testPart2(new BulkArrayDeque<Integer>(Integer.class))) {
            System.err.println("Test failed!");
            System.exit(-1);
        }
    }
}
