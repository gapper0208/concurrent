package com.gao.a_threadlocal;

import java.util.Random;

// ��һ���߳��У�����f1()��f2()����

public class App2 {
	static int n;
	static void f1() {
		n = new Random().nextInt();
		System.out.println("f1����:" + n);
	}
	static void f2() {
		System.out.println("f2��ȡ:" + n);
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
