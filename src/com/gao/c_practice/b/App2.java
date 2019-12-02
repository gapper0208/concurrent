package com.gao.c_practice.b;

/*
 	���4���̣߳����������߳�ÿ�ζ�j����1�����������߳�ÿ�ζ�j����1��д������
 	������߳�ִ�д��벻��ͬʱ������Ҫ���Runnable����װ��ͬ�Ĵ��롣
*/
class Data {
	private int j;
	
	public synchronized void increment() {
		j++;
		System.out.println(Thread.currentThread().getName() + "����:" + j);
	}
	
	public synchronized void decrement() {
		j--;
		System.out.println(Thread.currentThread().getName() + "����:" + j);
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
