package com.gao.c_practice.a;
/*
 	��100�Ż�Ʊ����4������ͬʱ��Ʊ��д������
 	����߳�ִ�е����������ͬʱ��ʹ��һ��Runnable����!
*/
class Ticket {
	private int num = 100;
	
	public synchronized void sell() {
		if (num > 0) {
			num--;
			System.out.println(Thread.currentThread().getName() + "����һ��Ʊ, ��ʣ��:" + num);
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
