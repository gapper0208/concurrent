package com.gao.g_semaphore.b;

import java.util.concurrent.TimeUnit;

// 只有一个共享对象的时候，synchronized就可以解决!
class BathRoom {
	private static int seed = 1;
	private final int id = seed++;

	public synchronized void work() {
		System.out.println(Thread.currentThread().getName() + "使用" + this + "开始");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "使用" + this + "结束");
	}

	@Override
	public String toString() {
		return "BathRoom [id=" + id + "]";
	}
}

class T implements Runnable {

	private BathRoom bathRoom;

	public T(BathRoom bathRoom) {
		super();
		this.bathRoom = bathRoom;
	}

	@Override
	public void run() {
		bathRoom.work();
	}
}

public class App {
	public static void main(String[] args) {
		BathRoom bathRoom = new BathRoom();
		T t = new T(bathRoom);

		for (int i = 1; i <= 5; i++) {
			new Thread(t).start();
		}
	}
}
