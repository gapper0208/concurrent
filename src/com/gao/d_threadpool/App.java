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
					System.out.println(Thread.currentThread().getName() + ": ִ������" + task + "��ʼ");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(task == 5) {
						// ���̹߳ҵ��󣬵�ǰ�̺߳�����һ�𱻶��������� �̳߳ػ���������һ���µ��̣߳�����ִ�к�������
						System.out.println(8 / 0);
					}
					System.out.println(Thread.currentThread().getName() + ": ִ������" + task + "����");
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
					System.out.println(Thread.currentThread().getName() + ": ִ������" + task + "��ʼ");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ": ִ������" + task + "����");
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
					System.out.println(Thread.currentThread().getName() + ": ִ������" + task + "��ʼ");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ": ִ������" + task + "����");
				}
			});
		}
		es.shutdown();
	}
}