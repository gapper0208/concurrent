package com.gao.a_threadlocal;

import java.util.Random;

// 使用ThreadLocal解决之前的问题！
public class App4 {
	
	static ThreadLocal<Integer> tl = new ThreadLocal<>();
	
	static void f1() {
		int n = new Random().nextInt();
		tl.set(n);
		System.out.println(Thread.currentThread().getName() + " f1产生:" + n);
	}
	static void f2() {
		int n = tl.get();
		System.out.println(Thread.currentThread().getName() + " f2获取:" + n);
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
