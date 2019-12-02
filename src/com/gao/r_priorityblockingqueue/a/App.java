package com.gao.r_priorityblockingqueue.a;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

// ��ӵ�PriorityBlockingQueue���ϵ�Ԫ�أ�����ʵ��java.lang.Comparable�ӿڣ����԰��Զ���ķ������Ƚϳ����ȼ�

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

	// ���ȼ�Խ�ߣ���Խ�ȱ���ȡ����
	@Override
	public int compareTo(Task o) {
		return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
	}
	
}

public class App {
	public static void main(String[] args) {
		Random r = new Random(47);
		PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
		// ���Ԫ��
		for (int i = 1; i <= 20; i++) {
			queue.put(new Task(r.nextInt(100)));
		}
		
		// ��ȡԪ�أ�ע�⣬�ǰ������ȼ�����ȡ�ģ�Ԫ�����ȼ�Խ�ߣ���Խ�ȱ���ȡ����
		try {
			while(!queue.isEmpty()) {
				queue.take().run();
			}
		} catch (InterruptedException e) {
			// Acceptable way to exit
		}
	}
}