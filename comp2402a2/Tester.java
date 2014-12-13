package comp2402a2;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Tester {
	
	/**
	 * Testing RandomQueue. Returns a random element.
	 * @param q
	 * @return
	 */
    public static boolean testPart1(Queue<Integer> q) {
        List<Boolean> conditions = new ArrayList<Boolean>();
    	int c = 0;
    	/*Time Complexity
        *   Times the process of adding one hundred thousand elements
        */
    	Stopwatch.start();
    	for(int i = 0 ; i < 100000; i++){
    		q.offer(i);
    	}
        Stopwatch.stop();
        System.out.println(Stopwatch.elapsedSeconds());

        conditions.add(Stopwatch.elapsedSeconds() < 1); // changed number of elements a
        /*Correctness*/
    	for(int i = 99999; i > 0 ; i--)
    		if(i==q.poll())
    			c++;
        conditions.add(c < 10);
        
        q.clear();
        
        for(int i = 0 ; i < 10 ; i++){
        	q.offer(i);
        }
        conditions.add(q.poll() != 9); //don't get the first element
    	return !conditions.contains(false); //if we contain false, return false
    }

    /**
     * BulkArrayDeque testing:
     * @param ad
     * @return
     */
    public static boolean testPart2(List<Integer> ad) {
        List<Boolean> conditions = new ArrayList<Boolean>();

    	Stopwatch.start();
        /* Create a collection to add from */
    	ArrayList<Integer> testCollection = new ArrayList<Integer>();
        ArrayDeque<Integer> argueTest = new ArrayDeque<Integer>(Integer.class);
        

        /*Time Complexity*/
        for(int i = 0 ; i < 100 ; i++)
            testCollection.add(i);
        for(int i = 0 ; i < 10_000 ; i++)
        	argueTest.addAll(testCollection);
        Stopwatch.start();
    	for(int i = 0 ; i < 10_000; i++){
           ad.addAll(testCollection);
        }
        Stopwatch.stop();
        System.out.println(Stopwatch.elapsedSeconds());
        conditions.add(Stopwatch.elapsedSeconds() <= 0.04);
        
        /*Correctness*/
        for(int i=0 ; i < ad.size() ; i++){
        	try{
        		conditions.add(ad.get(i).equals(argueTest.get(i)));
        	}catch(Exception e){
        		conditions.add(false);
        	}
        }
        /*Space Complexity*/
        conditions.add(!ad.contains(null));
    	return !conditions.contains(false); //if we contain false, return false
    }

    /**
     *  Tests Part 3 and 4.
     *  Time Complexity
     *  	Middle has to do the most shifting
     * @param ad
     * @return
     */
    public static boolean testPart3(List<Integer> ad) {
        List<Boolean> conditions = new ArrayList<Boolean>();
        ArrayDeque<Integer> argueTest = new ArrayDeque<Integer>(Integer.class);
        
        /*Time Complexity*/
        Stopwatch.start();
        for(int i = 0 ; i < 25_000 ; i++){
        	ad.add(ad.size()/2, i);
            argueTest.add(argueTest.size()/2, i);
        }
       
        Stopwatch.stop();
        System.out.println(Stopwatch.elapsedSeconds());
        conditions.add(Stopwatch.elapsedSeconds() <= ((ad instanceof ArrayDeque2) ? 1.34 : 1.44));
        /*Correctness*/
        conditions.add(!ad.contains(null));
        for(int i=0 ; i < argueTest.size() ; i++){
        	try{
        		conditions.add(ad.get(i).equals(argueTest.get(i)));
        	}catch(Exception e){
        		System.err.print(e);
        		conditions.add(false);
        	}
        }
       
        return !conditions.contains(false); 
    }
    
    /*
     *  Perfect!
     */
    public static boolean testPart5(List<Integer> ad) {
        List<Boolean> conditions = new ArrayList<Boolean>();
        List<Integer> test = new ArrayList<Integer>();

        Stopwatch.start();
        /*Time complexity*/
    	for(int i = 0 ; i < 100000; i++)
    		ad.add(0,i);
    	while(!ad.isEmpty()) ad.remove(0);

        for(int i = 0 ; i < 100000; i++){
            ad.add(ad.size(), i);
        }
        while(!ad.isEmpty()) ad.remove(ad.size()-1);
        Stopwatch.stop();
        System.out.println(Stopwatch.elapsedSeconds());
        conditions.add(Stopwatch.elapsedSeconds() < 2);
        
        /*Correctness*/
        
        for(int i = 0 ; i < 100; i++){
        	ad.add(ad.size()/2, i);
        	argueTest.add(argueTest.size()/2, i);
        }
        
        conditions.add(!ad.contains(null));
        
        for(int i=0 ; i < ad.size() ; i++){
        	try{
        		if(!ad.get(i).equals(test.get(i)))return false
        	}catch(Exception e){
        		conditions.add(false);
        	}
        }
    	return !conditions.contains(false);
    }
    


}

