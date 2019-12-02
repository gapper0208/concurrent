package com.gao.n_copyonwritearraylist.a;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/*
	CopyOnWriteArrayList这是一个ArrayList的线程安全的变体，其原理大概可以通俗的理解为:初始化的时候只有一个容器，很长一段时间，这个容器数据、
	数量等没有发生变化的时候，大家(多个线程)，都是读取(假设这段时间里只发生读取的操作)同一个容器中的数据，所以这样大家读到的数据都是唯一、一致、安全
	的，但是后来有人往里面增加了一个数据，这个时候CopyOnWriteArrayList底层实现添加的原理是先copy出一个容器(可以简称副本)，再往新的容器里添加
	这个新的数据，最后把新的容器的引用地址赋值给了之前那个旧的的容器地址，但是在添加这个数据的期间，其他线程如果要去读取数据，仍然是读取到旧的容器里的
	数据.
	
	所以CopyOnWriteArrayList适用于读多写少的情况，必须：黑名单、白名单
	
	该例子测试了在“写多读少”的情况下，CopyOnWriteArrayList效率确实低！
*/


public class App {
	public static void main(String[] args) throws Exception {
//		 如果直接使用ArrayList，则会有线程安全问题。
//		List<String> list = Collections.synchronizedList(new ArrayList());
		List<String> list = new CopyOnWriteArrayList<>();
		Random r = new Random();
		Thread[] array = new Thread[100];
		CountDownLatch latch = new CountDownLatch(array.length);
		
		long begin = System.currentTimeMillis();
		for (int i = 0; i < array.length; i++) {
			array[i] = new Thread(new Runnable() {
				public void run() {
					for (int j = 0; j < 1000; j++) {
						list.add("value" + r.nextInt(1000));
					}
					latch.countDown();
				}
			});
		}
		for (Thread th: array) {
			th.start();
		}
		latch.await();
		long end = System.currentTimeMillis();
		System.out.println("执行时间:" + (end - begin));
	}
}