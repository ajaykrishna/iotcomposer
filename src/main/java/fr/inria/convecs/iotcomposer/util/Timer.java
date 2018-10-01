package fr.inria.convecs.iotcomposer.util;

public class Timer {

	private long startTime;

	public Timer() {
		startTime = System.currentTimeMillis();
	}

	public double getElapsedTime() {
		long endTime = System.currentTimeMillis();
		return (double) (endTime - startTime) / (1000);
	}
}
