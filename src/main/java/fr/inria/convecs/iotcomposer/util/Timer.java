package fr.inria.convecs.iotcomposer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Timer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComposerExceptionMapper.class);

	private long startTime;

	public Timer() {
		startTime = System.currentTimeMillis();
	}

	public double getElapsedTime() {
		long endTime = System.currentTimeMillis();
		return (double) (endTime - startTime) / (1000);
	}
}
