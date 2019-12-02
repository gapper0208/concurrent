package com.gao.l_atomicinteger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// ��������ʾ��AtomicInteger�ķ��������;߱�ԭ����
// AtomicInteger�ײ�ʹ���˷�����ͬ����CAS����
public class App {
	AtomicInteger count = new AtomicInteger();
	void f1() {
		for (int i = 1; i <= 1000; i++) {
			count.incrementAndGet();
		}
	}
	public static void main(String[] args) {
		App app = new App();
		List<Thread> list = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			list.add(new Thread(new Runnable() {
				@Override
				public void run() {
					app.f1();
				}
			}));
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
		
		System.out.println(app.count.get());
	}
}