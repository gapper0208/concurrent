package com.gao.r_priorityblockingqueue.a;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

// 添加到PriorityBlockingQueue集合的元素，必须实现java.lang.Comparable接口，用以按自定义的方法来比较出优先级

class Task implements Runnable, Comparable<Task> {
	private static int counter = 0;
	private final int id = counter++;
	private int priority;
	
	public Task(int priority) {
		this.priority = priority;
	}
	
	@Override
	public void run() {
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		return "[" + priority + "] Task " + id;
	}

	// 优先级越高，则越先被获取出来
	@Override
	public int compareTo(Task o) {
		return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
	}
	
}

public class App {
	public static void main(String[] args) {
		Random r = new Random(47);
		PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
		// 添加元素
		for (int i = 1; i <= 20; i++) {
			queue.put(new Task(r.nextInt(100)));
		}
		
		// 获取元素，注意，是按照优先级来获取的，元素优先级越高，则越先被获取出来
		try {
			while(!queue.isEmpty()) {
				queue.take().run();
			}
		} catch (InterruptedException e) {
			// Acceptable way to exit
		}
	}
}