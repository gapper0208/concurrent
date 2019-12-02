package com.gao.i_countdownlatch.b;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class Player implements Runnable {

	private CountDownLatch go,finish;
	
	public Player(CountDownLatch go, CountDownLatch finish) {
		this.go = go;
		this.finish = finish;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+": 准备就绪");
		try {
			go.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+": 我跑!");
		try {
			TimeUnit.SECONDS.sleep(new Random().nextInt(10) + 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+": 到达终点!");
		finish.countDown();
	}
}

class Referee implements Runnable {
	
	private CountDownLatch go,finish;
	public Referee(CountDownLatch go, CountDownLatch finish) {
		this.go = go;
		this.finish = finish;
	}
	@Override
	public void run() {
		System.out.println("Press enter to start the match!");
		try {
			System.in.read();
			go.countDown();
			
			finish.await();
			System.out.println("比赛结束!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class App {
	public static void main(String[] args) throws Exception {
		CountDownLatch go = new CountDownLatch(1) , finish = new CountDownLatch(5);
		
		Player p = new Player(go, finish);
		Referee r = new Referee(go, finish);
		new Thread(r).start();
		TimeUnit.MILLISECONDS.sleep(1);
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
	}
}
