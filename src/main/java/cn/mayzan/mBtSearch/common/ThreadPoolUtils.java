package cn.mayzan.mBtSearch.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {

	private ThreadPoolUtils() {
	};

	private static class SingletonHolder {
		private final static ExecutorService instance = Executors.newFixedThreadPool(Constants.THREAD_POOL_THREADS);
	}

	public static ExecutorService getFixedThreadPool() {
		return SingletonHolder.instance;
	}

}
