package com.gao.a_threadlocal;

import java.util.Random;

// ʹ��ThreadLocal���֮ǰ�����⣡
public class App4 {
	
	static ThreadLocal<Integer> tl = new ThreadLocal<>();
	
	static void f1() {
		int n = new Random().nextInt();
		tl.set(n);
		System.out.println(Thread.currentThread().getName() + " f1����:" + n);
	}
	static void f2() {
		int n = tl.get();
		System.out.println(Thread.currentThread().getName() + " f2��ȡ:" + n);
	}	
	public static void main(String[] args) {
		for (int i = 1; i <= 2; i++) {
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					f1();
					f2();
				}
			});
			th.start();
		}
	}
}
