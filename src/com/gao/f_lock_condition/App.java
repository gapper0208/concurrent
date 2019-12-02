package com.gao.f_lock_condition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
	ReetrantLock��synchronized������:
	1. ReetrantLock�������ǿ��жϵģ�synchronized�������ǲ����жϵ�
	2. ��ƽ��������̵߳ȴ�ͬһ����ʱ�����밴����������ʱ��˳��������
	   ReentrantLockĬ�ϵĹ������������Ƿǹ�ƽ��������ͨ���������������true��������ƽ����
	       ��ƽ�������ܲ��Ǻܺã�
	   synchronized���Ƿǹ�ƽ����
	3.    һ��ReentrantLock�������ͬʱ�󶨶���������� 
	4. Lock������ȫ����synchronized������synchronized��һ���ܴ���Lock
	
	��������ʾ��Lock����synchronized��Ч��
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
