package com.gao.k_volatile.b;

import java.util.ArrayList;
import java.util.List;

// 该例子演示了，volatile并不能保证原子性

public class App {
	volatile int count = 0;

	/* synchronized */ void f1() {
		for (int i = 1; i <= 1000; i++) {
			count++;
		}
	}
	
	public static void main(String[] args) {
		App app = new App();
		
		List<Thread> list = new ArrayList<>();
		for(int i = 1; i <= 10; i++) {
			Thread th = new Thread(new Runnable() {
				public void run() {
					app.f1();
				}
			});
			list.add(th);
		}
		
		for (Thread th : list) {
			th.start();
		}
		for (Thread th : list) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(app.count);
		
	}
}
