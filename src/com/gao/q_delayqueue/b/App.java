package com.gao.q_delayqueue.b;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class DelayedTask implements Runnable,Delayed {
	private static int counter = 0;
	private final int id = counter++;
	private final int delta;
	private final long trigger;
	protected static List<DelayedTask> sequence = new ArrayList<>();
	
	public DelayedTask(int delayInMilliseconds) {
		delta = delayInMilliseconds;
		trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
		sequence.add(this);
	}
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
	}
	
	@Override
	public int compareTo(Delayed o) {
		DelayedTask that = (DelayedTask) o;
		if(trigger < that.trigger) return - 1;
		if(trigger > that.trigger) return 1;
		return 0;
	}
	
	@Override
	public void run() {
		System.out.print(this);
	}
	@Override
	public String toString() {
		return String.format("[%1$-4d]", delta) + " Task " + id;
	}
	public String summary() {
		return "(" + id + ":" + delta + ")";
	}
	
	public static class EndSentinel extends DelayedTask {
		private ExecutorService exec;
		public EndSentinel(int delay, ExecutorService e) {
			super(delay);
			exec = e;
		}
		public void run() {
			System.out.println();
			for (DelayedTask dt : DelayedTask.sequence) {
				System.out.print(dt.summary() + " ");
			}
			System.out.println();
			System.out.println(this + " Calling shutdownNow()");
			exec.shutdownNow();
		}
	}
}

class DelayedTaskConsumer implements Runnable {
	private DelayQueue<DelayedTask> q;
	public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
		this.q = q;
	}
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				q.take().run(); // Run task with the current thread
			}
		} catch (InterruptedException e) {
			// Acceptable way to exit
		}
		System.out.println("Finished DelayedTaskConsumer");
	}
}

public class App {
	public static void main(String[] args) {
		Random rand = new Random(47);
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<DelayedTask> queue = new DelayQueue<>();
		// Fill with tasks that have random delays:
		for(int i = 0; i < 20; i++) {
			queue.put(new DelayedTask(rand.nextInt(5000)));
		}
		// Set the stopping point
		queue.add(new DelayedTask.EndSentinel(5000, exec));
		exec.execute(new DelayedTaskConsumer(queue));
	}
}