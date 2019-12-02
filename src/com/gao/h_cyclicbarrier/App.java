package com.gao.h_cyclicbarrier;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

class T implements Runnable {
	
	private CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
		int count = 0;
		@Override
		public void run() {
			count++;
			if(count < 3) {
				System.out.println("人到齐啦， 向下一个景点出发!");
			} else {
				System.out.println("人到齐啦， 该休息啦，明天继续!");
			}
		}
	});
	
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+ "出发");
			TimeUnit.SECONDS.sleep(new Random().nextInt(10) + 1);
			System.out.println(Thread.currentThread().getName() + "到达兵马俑: " + cb.getNumberWaiting());
			
			cb.await();
			
			TimeUnit.SECONDS.sleep(new Random().nextInt(10) + 1);
			System.out.println(Thread.currentThread().getName() + "到达钟楼: " + cb.getNumberWaiting());
			
			cb.await();
			
			TimeUnit.SECONDS.sleep(new Random().nextInt(10) + 1);
			System.out.println(Thread.currentThread().getName() + "到达大明宫: " + cb.getNumberWaiting());
			
			cb.await();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class App {
	public static void main(String[] args) {
		T t = new T();
		for (int i = 1; i <= 3; i++) {
			new Thread(t).start();
		}
	}
}