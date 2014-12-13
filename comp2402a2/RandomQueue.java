package comp2402a2;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * An implementation of a RandomQueue.  This is like a standard queue,
 * except that when we remove an element, we get a random element.
 * 
 * TODO: This implementation requires Theta(size) time on average for
 * poll() operation.  Improve this so that it takes constant time.
 * @author morin
 *
 * @param <T> the type of elements stored in the queue
 */
public class RandomQueue<T> extends AbstractQueue<T> {
	/**
	 * The list that stores our queue elements
	 */
	List<T> l;
	Tester tester = new Tester();
	/**
	 * A source of random numbers
	 */

	Random r;
	
	/**
	 * The index of the next element we will return
	 */
	int next;

	public RandomQueue() {
		l = new ArrayList<T>();
		r = new Random();
	}

	/**
	 * Return an iterator for the elements in the queue
	 * Note: this iterator does not necessarily enumerate the elements
	 * in random order
	 */
	public Iterator<T> iterator() {
		return l.iterator();
	}

	public int size() {
		return l.size();
	}

	public boolean offer(T x) {
		l.add(x);
		next = r.nextInt(l.size());
		return true;
	}

	/**
	 * Return at the next element that will be returned by poll()/remove()
	 * without actually removing it
	 */
	public T peek() {
		if (l.size() == 0)
			return null;
		assert(next >= 0 && next <= l.size()-1);
		return l.get(next);
	}

	/**
	 * Remove and return a random element from the queue
	 */
	public T poll() {
		if (l.size() == 0)
			return null;
		assert(next >= 0 && next <= l.size()-1);
		T y =l.get(next); //o(4);
		//sets value at random index to last value (constant time)
		l.set(next, l.get(l.size()-1) ); //o(4)
		//sets last index to our random number
		l.set(l.size()-1, y); //o(3)
		//removes the last index and returns it
		T x = l.remove(l.size()-1);
		next = (l.size() > 0) ? r.nextInt(l.size()) : -1;
		return x;
	}

	/**
	 * A stupid method to help with tests
	 * @param b
	 * @throws AssertionError
	 */
	protected static void myassert(boolean b) throws AssertionError {
		if (!b) {
			throw new AssertionError();
		}
	}

	/**
	 * Some test code
	 * @param args ignored
	 */
    public static void main(String[] args) {
        if (!Tester.testPart1(new RandomQueue<Integer>())) {
            System.err.println("Test failed!");
            System.exit(-1);
        }else System.out.println("Test Passed");
    }
}
