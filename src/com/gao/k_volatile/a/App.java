package com.gao.k_volatile.a;

import java.util.concurrent.TimeUnit;

// 本例用来测试volatile所修饰的变量对于线程的可见性
// 注意，在不使用volatile的情況下，在while循环中打印b，也能强制线程去主内存中刷新b的值。

public class App {
	/* volatile */ boolean b = true;
	
	void f1() {
		System.out.println("start");
		while(b){}
		System.out.println("end");
	}
	
	public static void main(String[] args) {
		App app = new App();
		new Thread(new Runnable() {
			@Override
			public void run() {
				app.f1();
			}
		}).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		app.b = false;
		System.out.println("set b to false..");
	}
}