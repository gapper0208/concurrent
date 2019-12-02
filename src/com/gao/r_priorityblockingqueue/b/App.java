package com.gao.r_priorityblockingqueue.b;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

// PrioritizedTask��һ���޽���У����Կ�������put����������������

class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {

	private Random rand = new Random(47);
	private static int counter = 0;
	private final int id = counter++;
	private final int priority;
	
	// ��¼����PrioritizedTask��˳��
	protected static List<PrioritizedTask> sequence = new ArrayList<>();
	
	public PrioritizedTask(int priority) {
		this.priority = priority;
		sequence.add(this);
	}
	
	// ���ȼ�����Խ�󣬾�Խ�ȴӶ�����ȡ��
	@Override
	public int compareTo(PrioritizedTask o) {
		return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
	}

	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		return String.format("[%1$-3d]", priority) + " Task " + id;
	}
	
	public String summary() {
		return "("+ id + ":" + priority +")";
	}
	
	public static class EndSentinel extends PrioritizedTask {
		private ExecutorService exec;
		public EndSentinel(ExecutorService e) {
			super(-1); // Lowest priority in this program
			exec = e;
		}
		@Override
		public void run() {
			int count = 0;
			for (PrioritizedTask pt : PrioritizedTask.sequence) {
				System.out.print(pt.summary());
				if(++count % 5 == 0) {
					System.out.println();
				}
			}
			System.out.println();
			System.out.println(this + " Calling shutdownNow()");
			exec.shutdownNow();
		}
		
	}
}

class PrioritizedTaskProducer implements Runnable {
	private Random rand = new Random(47);
	private Queue<Runnable> queue;
	private ExecutorService exec;
	public PrioritizedTaskProducer(Queue<Runnable> q, ExecutorService e) {
		queue = q;
		exec = e; // Used for EndSentinel
	}
	@Override
	public void run() {
		// Unbounded queue; never blocks.
		// Fill it up fast with random priorities;
		for (int i = 0; i < 20; i++) {
			queue.add(new PrioritizedTask(rand.nextInt(10)));
			Thread.yield();
		}
		// Trickle in highest-priority jobs;
		try {
			for (int i = 0; i < 10; i++) {
				TimeUnit.MILLISECONDS.sleep(250);
				queue.add(new PrioritizedTask(10));
			}
			// Add jobs, lowest priority first:
			for (int i = 0; i < 10; i++) {
				queue.add(new PrioritizedTask(i));
			}
			// A sentinel to stop all the tasks:
			queue.add(new PrioritizedTask.EndSentinel(exec));
		} catch (InterruptedException e) {
			// Acceptable way to exit
		}
		System.out.print("Finished PrioritizedTaskProducer");
	}
}

class PrioritizedTaskConsumer implements Runnable {
	private PriorityBlockingQueue<Runnable> q;
	public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
		this.q = q;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				// Use current thread to run the task:
				q.take().run();
			}
		} catch (InterruptedException e) {
			// Acceptable way to exit
		}
		System.out.println("Finished PrioritizedTaskConsumer");
	}
}

public class App {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
		exec.execute(new PrioritizedTaskProducer(queue, exec));
		exec.execute(new PrioritizedTaskConsumer(queue));
	}
}