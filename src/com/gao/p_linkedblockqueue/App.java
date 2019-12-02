package com.gao.p_linkedblockqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

// LinkedBlockingQueue是BlockingQueue的另外一个实现类，只不过底层是链表而已
// 其add put offer方法与ArrayBlockingQueue具有一样的特性

// put方法在队列满的时候会阻塞直到有队列成员被消费，take方法在队列空的时候会阻塞，直到有队列成员被放进来。

public class App {
	
	public static void main(String[] args) {
		BlockingQueue<String> queue = new LinkedBlockingQueue<>(3);
		Random r = new Random();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						queue.put("value"+r.nextInt(1000));
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "producer").start();
		
		for(int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						try {
							System.out.println(Thread.currentThread().getName() + " - " + queue.take());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}, "consumer"+i).start();
		}
		
	}
	
}