package com.gao.c_practice.b;

/*
 	设计4个线程，其中两个线程每次对j增加1，另外两个线程每次对j减少1。写出程序。
 	当多个线程执行代码不相同时，就需要多个Runnable来封装不同的代码。
*/
class Data {
	private int j;
	
	public synchronized void increment() {
		j++;
		System.out.println(Thread.currentThread().getName() + "增加:" + j);
	}
	
	public synchronized void decrement() {
		j--;
		System.out.println(Thread.currentThread().getName() + "减少:" + j);
	}
}

class T implements Runnable {
	private Data data;
	public T(Data data) {
		this.data = data;
	}
	@Override
	public void run() {
		while(true) {
			data.increment();
		}
	}
}

class T2 implements Runnable {
	private Data data;
	public T2(Data data) {
		this.data = data;
	}
	@Override
	public void run() {
		while(true) {
			data.decrement();
		}
	}
}

public class App2 {
	public static void main(String[] args) {
		Data data = new Data();
		for (int i = 0; i < 2; i++) {
			new Thread(new T(data)).start();
			new Thread(new T2(data)).start();
		}
	}
}
