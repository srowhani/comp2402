package comp2402a2;

import java.util.AbstractList;

/**
 * An implementation of the List interface that allows for fast modifications
 * at both the head and tail.
 *
 * @param <T> the type of objects stored in this list
 */
public class ArrayDeque2<T> extends AbstractList<T> {
	/**
	 * The class of elements stored in this queue
	 */
	protected Factory<T> f;
	
	// The following are made public on purpose - for testing purposes
	
	/**
	 * Array used to store elements
	 */
	public T[] a;
	
	/**
	 * Index of next element to de-queue
	 */
	public int j;
	
	/**
	 * Number of elements in the queue
	 */
	public int n;
	
	/**
	 * Grow the internal array
	 */
	public String toString(){
		resize();
		return super.toString();
	}
	
	
	protected void resize() {
		T[] b = f.newArray(Math.max(2*n, 1));
		
		for(int i = 0 ; i < n ; i++){
			j = ( ( j + i ) >= a.length) ? -1 : j; //if i past a's length, set j to zero and loop, otherwise j is j; 
			b[n/2 + i] = a[j+i]; //place a[j+i] which is an increment + the head at n/2 which basically adjusts for our shifting in add
		}
		j = n / 2; //the new head (which is half of the number of elements we have)
		a = b; //set old to new
	}
	
	/**
	 * Constructor
	 */
	public ArrayDeque2(Class<T> t) {
		f = new Factory<T>(t);
		a = f.newArray(1);
		j = 0;
		n = 0;
	}
	
	public int size() {
		return n;
	}
	
	public T get(int i) {
		if (i < 0 || i > n-1) throw new IndexOutOfBoundsException();
		return a[j+i];  // look, no mod
	}
	
	public T set(int i, T x) {
		if (i < 0 || i > n-1) throw new IndexOutOfBoundsException();
		T y = a[j+i];   // look, no mod
		a[j+i] = x;
		return y;
	}
	
	public void add(int i, T x) {
		// TODO: modify this to avoid running off either end
		if (i < 0 || i > n) throw new IndexOutOfBoundsException();
		
		if (n + j + 1 > a.length) resize(); //sum the head index and the number of elements to see if its >= len(a)
		if (i < n/2) {	// shift a[0],..,a[i-1] left one position
			j = (j == 0) ? a.length - 1 : j - 1; // (j-1) mod a.length
			for (int k = 0; k <= i-1; k++)
				a[j+k] = a[j+k+1];
		} else {	    // shift a[i],..,a[n-1] right one position
			for (int k = n; k > i; k--)
				a[j+k] = a[j+k-1];
		}
		a[j+i] = x;
		n++;
	}
	
	public T remove(int i) {
		if (i < 0 || i > n - 1)	throw new IndexOutOfBoundsException();
		int r = (j+i) >= a.length ? -1 : j;
		T x = a[r+i];
		if (i < n/2) {  // shift a[0],..,[i-1] right one position
			for (int k = i; k >= 0; k--){
				a[r+k] = a[r+(k-1)];
			}j++;
		} else {        // shift a[i+1],..,a[n-1] left one position
			for (int k = i; k < n; k++)
				a[r+k] = a[r+k+1];
		}
		n--;
		if (3*n < a.length) resize();
		return x;
	}
	
	public void clear() {
		n = 0;
		resize();
	}

    public static void main(String[] args) {
        if (!Tester.testPart3(new ArrayDeque2<Integer>(Integer.class))) {
            System.err.println("Test failed!");
            System.exit(-1);
        }
    }

}