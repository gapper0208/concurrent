package com.gao.f_lock_condition;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 该例子演示了一个Lock对象可以绑定多个Condition对象
class Shelf {
	private String[] strs = { "○", "○", "○", "○", "○", "○" };
	private int i;

	private Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();

	public void put() {
		lock.lock();
		try {
			while (isFull()) {
				try {
					notFull.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			strs[i++] = "●";
			System.out.println(Thread.currentThread().getName() + "放入:" + this);
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public void get() {
		lock.lock();
		try {

			while (isEmpty()) {
				try {
					notEmpty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			strs[--i] = "○";
			System.out.println(Thread.currentThread().getName() + "取出:" + this);
			notFull.signal();
		} finally {
			lock.unlock();
		}
	}

	public boolean isFull() {
		return i == strs.length;
	}

	public boolean isEmpty() {
		return i == 0;
	}

	@Override
	public String toString() {
		return Arrays.toString(strs);
	}

}

class T implements Runnable {
	private Shelf shelf;

	public T(Shelf shelf) {
		this.shelf = shelf;
	}

	@Override
	public void run() {
		while (true) {
			shelf.put();
		}
	}
}

class T2 implements Runnable {
	private Shelf shelf;

	public T2(Shelf shelf) {
		this.shelf = shelf;
	}

	@Override
	public void run() {
		while (true) {
			shelf.get();
		}
	}
}

public class App4 {
	public static void main(String[] args) {
		Shelf shelf = new Shelf();
		T t = new T(shelf);
		T2 t2 = new T2(shelf);

		Thread th = new Thread(t);
		Thread th2 = new Thread(t);
		Thread th3 = new Thread(t2);
		Thread th4 = new Thread(t2);
		th.start();
		th2.start();
		th3.start();
		th4.start();
	}
}