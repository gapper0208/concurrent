package com.gao.a_threadlocal;

import java.util.Random;

class DataUtils {
	private static ThreadLocal<Integer> tl = new ThreadLocal<>();
	
	public static Integer getData() {
		Integer n = tl.get();
		if(n == null) {
			n = new Random().nextInt();
			tl.set(n);
		}
		return n;
	}
}
class A {
	public void f1() {
		System.out.println(Thread.currentThread().getName() + " f1£º" + DataUtils.getData());
	}
}
class B {
	public void f2() {
		System.out.println(Thread.currentThread().getName() + " f2£º" + DataUtils.getData());
	}
}
class T implements Runnable {
	@Override
	public void run() {
		new A().f1();
		new B().f2();
	}
}
public class App5 {
	public static void main(String[] args) {
		for (int i = 1; i <= 2; i++) {
			Thread th = new Thread(new T());
			th.start();
		}
	}
}
