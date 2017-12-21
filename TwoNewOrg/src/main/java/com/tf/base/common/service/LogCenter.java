package com.tf.base.common.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogCenter {

	
	private static ExecutorService logExecutor = Executors
			.newFixedThreadPool(1);
	
	public static void addLogTask(Runnable command) {
		logExecutor.execute(command);
	}
}
