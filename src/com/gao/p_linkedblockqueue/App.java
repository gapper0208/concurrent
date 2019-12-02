package com.gao.p_linkedblockqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

// LinkedBlockingQueue��BlockingQueue������һ��ʵ���ֻ࣬�����ײ����������
// ��add put offer������ArrayBlockingQueue����һ��������

// put�����ڶ�������ʱ�������ֱ���ж��г�Ա�����ѣ�take�����ڶ��пյ�ʱ���������ֱ���ж��г�Ա���Ž�����

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