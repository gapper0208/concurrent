package com.gao.q_delayqueue.a;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// ��������ʾ��DelayQueue�ļ�ʹ��
// DelayQueue���޽�ģ�Ҳ����˵����������������putԪ��
// DelayQueue�в������nullԪ��

class Task implements Delayed {

	private long time;
	
	public Task(long time) {
		this.time = System.currentTimeMillis() + time;
	}
	
	@Override
	public long getDelay(TimeUnit unit) {
		long l = unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
//		������
//		System.out.println("��ǰϵͳʱ��:" + new Date());
//		System.out.println(this + "����" + l + "����...");
		return l;
	}
	
	// һ��Ҫ���ӳ���С��������ڵ�һλ, ����һ��Ҫ���ӳ�ʱ���������������
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