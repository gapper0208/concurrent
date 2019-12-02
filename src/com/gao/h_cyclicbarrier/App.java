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
				System.out.println("�˵������� ����һ���������!");
			} else {
				System.out.println("�˵������� ����Ϣ�����������!");
			}
		}
	});
	
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName()+ "����");
			TimeUnit.SECONDS.sleep(new Random().nextInt(10) + 1);
			System.out.println(Thread.currentThread().getName() + "�������ٸ: " + cb.getNumberWaiting());
			
			cb.await();
			
			TimeUnit.SECONDS.sleep(new Random().nextInt(10) + 1);
			System.out.println(Thread.currentThread().getName() + "������¥: " + cb.getNumberWaiting());
			
			cb.await();
			
			TimeUnit.SECONDS.sleep(new Random().nextInt(10) + 1);
			System.out.println(Thread.currentThread().getName() + "���������: " + cb.getNumberWaiting());
			
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