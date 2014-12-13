package comp2402a3;

public class Tester {
	
	
	private static Interval<Integer> createPoint(int i, int j){
		return new Interval<Integer>(i,j);
	}
	public static boolean testPart1(IntervalSet<Integer> dis) {
		// Your code goes here
		return false;
	}

	public static boolean testPart2(IntervalSet<Integer> dis) {
		dis.add(createPoint(1,5));
		dis.add(createPoint(0,14));
		dis.add(createPoint(15,20));
		dis.add(createPoint(-3,0));
		dis.add(createPoint(21,21));
		dis.add(createPoint(0,22));

		dis.add(createPoint(1,18));


		System.out.println(dis);
		//z1 is indeed bigger than z
		return true;
	}
}
