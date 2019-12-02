package com.gao.d_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import sun.nio.ch.ThreadPool;

public class App {
	public static void main(String[] args) {
		// testFixedThreadPool();
		testCachedThreadPool();
		// testSingleThreadPool();
		// testScheduledThreadPool();
	}

	private static void testScheduledThreadPool() {
		ScheduledExecutorService es = Executors.newScheduledThreadPool(3);
		es.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("boom!!");
			}
		}, 3, 1,TimeUnit.SECONDS);
		//es.shutdown();
	}

	private static void testSingleThreadPool() {
		ExecutorService es = Executors.newSingleThreadExecutor();
		for(int i = 1; i <= 10; i++) {
			final int task = i;
			es.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ": 执行任务" + task + "开始");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(task == 5) {
						// 当线程挂掉后，当前线程和任务一起被丢弃，接着 线程池会立即创建一个新的线程，继续执行后续任务！
						System.out.println(8 / 0);
					}
					System.out.println(Thread.currentThread().getName() + ": 执行任务" + task + "结束");
				}
			});
		}
		es.shutdown();
	}

	private static void testCachedThreadPool() {
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i = 1; i <= 10; i++) {
			final int task = i;
			es.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ": 执行任务" + task + "开始");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ": 执行任务" + task + "结束");
				}
			});
		}
		es.shutdown();
	}

	private static void testFixedThreadPool() {
		ExecutorService es = Executors.newFixedThreadPool(3);
		for(int i = 1; i <= 10; i++) {
			final int task = i;
			es.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ": 执行任务" + task + "开始");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ": 执行任务" + task + "结束");
				}
			});
		}
		es.shutdown();
	}
}