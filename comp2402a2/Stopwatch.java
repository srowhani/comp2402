package comp2402a2;

public class Stopwatch {
	static protected long start, stop;
	
	static public void start() {
		start = System.nanoTime();
	}
	
	static public void stop() {
		stop = System.nanoTime();
	}
	
	static public double elapsedSeconds() {
		return (stop-start)*1e-9;
	}
}
