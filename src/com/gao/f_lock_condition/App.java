package com.gao.f_lock_condition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
	ReetrantLock和synchronized的区别:
	1. ReetrantLock的阻塞是可中断的，synchronized的阻塞是不可中断的
	2. 公平锁，多个线程等待同一个锁时，必须按照申请锁的时间顺序获得锁，
	   ReentrantLock默认的构造器创建的是非公平锁，可以通过向构造器传入参数true来创建公平锁，
	       公平锁的性能不是很好！
	   synchronized锁是非公平锁。
	3.    一个ReentrantLock对象可以同时绑定多个条件对象 
	4. Lock可以完全代替synchronized，但是synchronized不一定能代替Lock
	
	该例子演示了Lock代替synchronized的效果
*/
public class App {
	static class Outputer {
		private Lock lock = new ReentrantLock();
		public  void print(String str) {
			while (true) {
					try {
						lock.lock();
						for (int i = 0; i < str.length(); i++) {
							char c = str.charAt(i);
							System.out.print(c);
						}
					} finally {
						System.out.println();
						lock.unlock();
					}
			}
		}
	}
	
	static class T implements Runnable {
		private Outputer outputer;
		public T(Outputer outputer) {
			this.outputer = outputer;
		}
		@Override
		public void run() {
			outputer.print("abcd");
		}
	}
	
	static class T2 implements Runnable {
		private Outputer outputer;
		public T2(Outputer outputer) {
			this.outputer = outputer;
		}
		@Override
		public void run() {
			outputer.print("1234");
		}
	}
	
	public static void main(String[] args) {
		Outputer outputer = new Outputer();
		
		new Thread(new T(outputer)).start();
		new Thread(new T2(outputer)).start();
	}
}
