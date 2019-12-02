package com.gao.f_lock_condition;

import java.util.concurrent.locks.ReentrantLock;

/*
 	��������ʾ������ReentrantLock������ƽ���ͷǹ�ƽ�������ֳ��Ĳ�ͬ���..
 	�Աȣ�synchronized�Ƿǹ�ƽ��
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
					// ������´��룬�ٹ۲����������������
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
	
	// synchronized�Ƿǹ�ƽ��
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

