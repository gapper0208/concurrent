package com.gao.a_threadlocal;

import java.util.Random;

// 在一个线程中，调用f1()和f2()方法

public class App2 {
	static int n;
	static void f1() {
		n = new Random().nextInt();
		System.out.println("f1产生:" + n);
	}
	static void f2() {
		System.out.println("f2获取:" + n);
	}	
	public static void main(String[] args) {
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
