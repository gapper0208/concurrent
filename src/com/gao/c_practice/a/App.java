package com.gao.c_practice.a;
/*
 	有100张火车票，有4个窗口同时卖票，写出程序。
 	多个线程执行的任务代码相同时，使用一个Runnable即可!
*/
class Ticket {
	private int num = 100;
	
	public synchronized void sell() {
		if (num > 0) {
			num--;
			System.out.println(Thread.currentThread().getName() + "卖出一张票, 还剩余:" + num);
		}
	}
	
	public int getNum() {
		return num;
	}
}
class SellTask implements Runnable{
	private Ticket ticket;
	public SellTask(Ticket ticket) {
		this.ticket = ticket;
	}
	@Override
	public void run() {
		while(ticket.getNum() > 0) {
			ticket.sell();
		}
	}
}
public class App {
	public static void main(String[] args) {
		Ticket t = new Ticket();
		SellTask sellTask = new SellTask(t);
		
		for(int i=1; i<=4; i++) {
			new Thread(sellTask).start();
		}
	}
}
