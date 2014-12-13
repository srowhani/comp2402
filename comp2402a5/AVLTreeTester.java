package comp2402a5;

import java.util.Set;
import java.util.TreeSet;

public class AVLTreeTester {
	private static double getTime(){
		return System.nanoTime() * 1e-9;
	}
    public static boolean testAVLTree(AVLTree<Integer> t) {
        //========================================
    	/**
    	 *  Time Complexity
    	 *  ================
    	 *  Since the AVL Tree is an implementation of the
    	 *  SortedSet, it should theoretically take at most
    	 *  O(log(n)) time to add all n elements.
    	 *  
    	 *  In our case, we will add 10,000.
    	 *  	Since there's no easy way to know how fast our computer
    	 *  	will add these elements, we will compare speeds to a TreeSet.
    	 *      If the time difference is <= 1 second, then it's a success.
    	 */
    	Set<Integer> SSet = new TreeSet<Integer>();
    	double start = getTime();
        for(int i = 0 ; i < 1_000_000 && t.add(t.rand.nextInt(100_000)); i++);
        double elapsed = getTime() - start;
        start = getTime();
        for(int i = 0 ; i < 1_000_000 && SSet.add(t.rand.nextInt(100_000)); i++);
        if(elapsed - (getTime() - start) > 1)
        	return false;
        SSet.clear();
        t.clear();
        //========================================
        /**
         * Correctness
         * ============
         *  Should behave exactly like a SSet in this example, meaning
         *  that all the elements we add to the SSet should also be in our
         *  AVLtree. If there ever exists something in either that doesn't
         *  exist in both. BAM return false
         */
        /*Adding*/
        for(int i = 0 ; i < 100_000 ; i++){
        	int el = t.rand.nextInt(200);
        	boolean a = t.add(el);
        	try{
        		t.checkHeights(t.r);
        	}catch(RuntimeException e){
        		return false;
        	}
        	if(a != SSet.add(el))
        		return false;
        }
        for(int i = 0 ; i < 10_000 ; i++){
        	int remove = t.rand.nextInt(200);
        	boolean el = t.remove(remove);
        	try{
        		t.checkHeights(t.r);
        	}catch(RuntimeException e){
        		return false;
        	}
        	if(el != SSet.remove(remove))
        		return false;
        }
        return true;
    }
}
