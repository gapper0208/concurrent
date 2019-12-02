package com.gao.g_semaphore.c;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//只有2个共享对象的时候，synchronized无法解决
//必须使用semaphore来解决!
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

	private List<BathRoom> bathRoomList;
	private Semaphore s;

	public T(List<BathRoom> bathRoomList, Semaphore s) {
		this.bathRoomList = bathRoomList;
		this.s = s;
	}

	@Override
	public void run() {
		try {
			s.acquire();
			BathRoom bathRoom = bathRoomList.remove(0);
			bathRoom.work();
			bathRoomList.add(bathRoom);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			s.release();
		}
	}
}

public class App {
	public static void main(String[] args) {
		List<BathRoom> bathRoomList = new ArrayList<>(Arrays.asList(new BathRoom(), new BathRoom()));
		Semaphore s = new Semaphore(2);
		T t = new T(bathRoomList, s);

		for (int i = 1; i <= 5; i++) {
			new Thread(t).start();
		}
	}
}
