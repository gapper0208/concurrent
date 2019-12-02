package com.gao.o_arrayblockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// ArrayBlockingQueue是阻塞队列的一个实现类，底层是数组，
// 该集合的add、put、offer方法都是想集合中添加元素，但是有不同的特性

//put方法在队列满的时候会阻塞直到有队列成员被消费，take方法在队列空的时候会阻塞，直到有队列成员被放进来。

public class App {
	public static void main(String[] args)  {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
		for(int i = 1; i <=5 ; i++) {
			// add超出集合容量时，会抛出异常
//			 System.out.println("add method: " + queue.add("value" + i) );
			
			// put超出集合容量时，会阻塞
//			try {
//				queue.put("value" + i);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println("put method:" + i);
			
//			offer超出集合容量时，并不会阻塞，而是会立即返回false，当然也不会把元素加入集合中。
			System.out.println("offer method:" + queue.offer("value" + i));

			// offer的另一种重载形式，在超出集合容量时，会先阻塞一段时间，
			// 如果阻塞时间内容器有空位腾出的话，就会添加元素成功，
			// 如果阻塞超时，则返回false
//			try {
//				System.out.println("offer method:" + queue.offer("value" + i, 1, TimeUnit.SECONDS));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		
//		for(int i = 1; i <= 5; i ++) {
//			try {
//				System.out.println("take:" + queue.take());
//				System.out.println("take:" + queue.poll());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		System.out.println("over");
	}
}