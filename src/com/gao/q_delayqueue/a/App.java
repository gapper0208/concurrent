package com.gao.q_delayqueue.a;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// 该例子演示了DelayQueue的简单使用
// DelayQueue是无界的，也就是说，可以无限向其中put元素
// DelayQueue中不能添加null元素

class Task implements Delayed {

	private long time;
	
	public Task(long time) {
		this.time = System.currentTimeMillis() + time;
	}
	
	@Override
	public long getDelay(TimeUnit unit) {
		long l = unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
//		测试用
//		System.out.println("当前系统时间:" + new Date());
//		System.out.println(this + "还有" + l + "毫秒...");
		return l;
	}
	
	// 一定要把延迟最小的任务放在第一位, 所以一定要按延迟时间的升序排序任务
	@Override
	public int compareTo(Delayed o) {
		if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
			return 1;
		else if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
			return -1;
		return 0;
	}
	
	@Override
	public String toString() {
		return time + "";
	}
}

public class App {
	public static void main(String[] args) throws Exception {
		DelayQueue<Task> queue = new DelayQueue<>();
		queue.put(new Task(3000));
		queue.put(new Task(6000));
		queue.put(new Task(4000));
		queue.put(new Task(1000));
		
		System.out.println(queue);
		
		for(int i = 1; i <= 4; i++) {
			System.out.println(queue.take());
		}
	}
}