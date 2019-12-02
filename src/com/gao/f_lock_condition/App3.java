package com.gao.f_lock_condition;

import java.util.concurrent.locks.ReentrantLock;

/*
 	该例子演示了利用ReentrantLock创建公平锁和非公平锁锁表现出的不同结果..
 	对比：synchronized是非公平锁
*/
public class App3 {
	
	private static ReentrantLock lock = new ReentrantLock(true);
	
	static class T implements Runnable {
		@Override
		public void run() {
			for (int i = 1; i <= 5; i++) {
				lock.lock();
				try {
					System.out.println(Thread.currentThread().getName() + " get lock");
					// 添加以下代码，再观察结果，更有助于理解
//					try {
//						TimeUnit.SECONDS.sleep(1);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				} finally {
					lock.unlock();
				}
			}
		}
	}
	
	// synchronized是非公平锁
	static class T2 implements Runnable {
		@Override
		public void run() {
			for (int i = 1; i <= 5; i++) {
				synchronized (this) {
					System.out.println(Thread.currentThread().getName() + " get lock");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		T t = new T();
		Thread th = new Thread(t);
		Thread th2 = new Thread(t);
		th.start();
		th2.start();
	}
}

