package com.gao.g_semaphore.a;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class T implements Runnable {
	private Semaphore s = new Semaphore(2);
	
	@Override
	public void run() {
		try {
			s.acquire();
			System.out.println(Thread.currentThread().getName() + "开始执行");
			TimeUnit.SECONDS.sleep(3);
			System.out.println(Thread.currentThread().getName() + "结束执行");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			s.release();
		}
	}
}

public class App {
	public static void main(String[] args) {
		T t = new T();
		for (int i = 1; i <= 10; i++) {
			new Thread(t).start();
		}
	}
}