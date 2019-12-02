package com.gao.i_countdownlatch.a;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class Light implements Runnable {
	private CountDownLatch cd;
	public Light(CountDownLatch cd) {
		this.cd = cd;
	}
	@Override
	public void run() {
		System.out.println("prepare light!");
		try {
			TimeUnit.SECONDS.sleep(new Random().nextInt(10) + 1);
			cd.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("light ready!");
	}
}

class Music implements Runnable {
	private CountDownLatch cd;
	public Music(CountDownLatch cd) {
		this.cd = cd;
	}
	@Override
	public void run() {
		System.out.println("prepare music!");
		try {
			TimeUnit.SECONDS.sleep(new Random().nextInt(10) + 1);
			cd.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("music ready!");
	}
}

class Dancer implements Runnable {
	private CountDownLatch cd;
	public Dancer(CountDownLatch cd) {
		this.cd = cd;
	}
	@Override
	public void run() {
		System.out.println("prepare dancer!");
		try {
			TimeUnit.SECONDS.sleep(new Random().nextInt(10) + 1);
			cd.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("dancer ready!");
	}
}

class King implements Runnable {
	private CountDownLatch cd;
	public King(CountDownLatch cd) {
		this.cd = cd;
	}
	@Override
	public void run() {
		try {
			cd.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("天王出场:冷冷的冰雨在脸上胡乱的拍!");
	}
}

class Queen implements Runnable {
	private CountDownLatch cd;
	public Queen(CountDownLatch cd) {
		this.cd = cd;
	}
	@Override
	public void run() {
		try {
			cd.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("天后出场:我家住在黄土高坡~~!");
	}
}

public class App {
	public static void main(String[] args) {
		CountDownLatch cd = new CountDownLatch(3);
		
		Light light = new Light(cd);
		Music music = new Music(cd);
		Dancer dancer = new Dancer(cd);
		King king = new King(cd);
		Queen queen = new Queen(cd);
		
		new Thread(light).start();
		new Thread(music).start();
		new Thread(dancer).start();
		new Thread(king).start();
		new Thread(queen).start();
	}
}