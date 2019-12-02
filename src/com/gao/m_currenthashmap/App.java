package com.gao.m_currenthashmap;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

// ��������ʾ�ˣ�ͬ����ͬ��������ConcurrentHashMap��Ч��Ҫ����!
// jdk1.7�µ�ConcurrentHashMap�ײ��Ƿֶ���
// jdk1.8�µ�ConcurrentHashMap�ײ���CAS+synchronizedʵ�ֵ�

public class App {
	public static void main(String[] args) throws Exception {
		// 1. 
//		Map<String, String> map = new Hashtable<>();
		// 2. 
//		Map<String, String> m = new HashMap<>();
//		Map<String, String> map = Collections.synchronizedMap(m);
		// 3.
		Map<String, String> map = new ConcurrentHashMap<>();
		Random r = new Random();
		Thread[] array = new Thread[100];
		CountDownLatch latch = new CountDownLatch(array.length);
		long begin = System.currentTimeMillis();
		for (int i = 0; i < array.length; i++) {
			array[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 200000; j++) {
						map.put("key"+r.nextInt(10000), "value"+r.nextInt(10000));
					}
					latch.countDown();
				}
			});
		}
		for (Thread th : array) {
			th.start();
		}
		latch.await();
		long end = System.currentTimeMillis();
		System.out.println("ִ��ʱ��:" + (end - begin));
		
	}
}

// ע�⣬ConcurrentHashMap�ļ���ֵ�ǲ�����Ϊnull��