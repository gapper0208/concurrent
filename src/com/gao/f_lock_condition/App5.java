package com.gao.f_lock_condition;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/*
 读写锁的使用，注意观察结果:
 1. 读的过程中可以有读，不可以有写
 2. 写的过程中不可以有读，也不可以有写
*/
public class App5 {
	static class Queue {
		private int data;
		private ReadWriteLock lock = new ReentrantReadWriteLock();
		public void get() {
			lock.readLock().lock();
			try {
				System.out.println(Thread.currentThread().getName() + " is ready to read data!");
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000) + 1);
				System.out.println(Thread.currentThread().getName() + " have read data: " + data);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.readLock().unlock();
			}
		}
		
		public void put() {
			lock.writeLock().lock();
			try {
				System.out.println(Thread.currentThread().getName() + " is ready to write data!");
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000) + 1);
				data = new Random().nextInt(100);
				System.out.println(Thread.currentThread().getName() + " have write data:" + data);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.writeLock().unlock();
			}
		}
	}
	
	
	static class T implements Runnable {
		private Queue q;
		public T(Queue q) {
			super();
			this.q = q;
		}
		@Override
		public void run() {
			while (true) {
				q.get();
			}
		}
	}
	
	static class T2 implements Runnable {
		private Queue q;
		public T2(Queue q) {
			super();
			this.q = q;
		}
		@Override
		public void run() {
			while (true) {
				q.put();
			}
		}
	}
	
	public static void main(String[] args) {
		Queue q = new Queue();
		T t = new T(q);
		T2 t2 = new T2(q);
		for(int i = 1; i <= 3; i++) {
			Thread th = new Thread(t);
			Thread th2 = new Thread(t2);
			th.start();
			th2.start();
		}
	}
	
}
