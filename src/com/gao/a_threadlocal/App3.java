package com.gao.a_threadlocal;

import java.util.Random;

/*
	制作两个线程，执行相同的任务：f1()产生数据， f2()获取数据。
	发现了问题：多数情况下，2个线程产生的数字竟然都是一样的！ 这是怎样的一种概率？？ 这种情况是不可能的！！
	
	eg. 
	Thread-1 f1产生:99318067
	Thread-0 f1产生:99318067
	Thread-1 f2获取:99318067
	Thread-0 f2获取:99318067
	
	你也许会想，把f1生成的随机数当做参数传递给f2方法不就完事了吗？  要知道，很多时候，方法不是我们自己定的，
	我们编码时，如果在使用别人已经规定好的方法，是没有办法去修改方法的参数的！  这就需要使用"线程本地变量了"！
 */


public class App3 {
	static int n;
	static void f1() {
		n = new Random().nextInt();
		System.out.println(Thread.currentThread().getName() + " f1产生:" + n);
	}
	static void f2() {
		System.out.println(Thread.currentThread().getName() + " f2获取:" + n);
	}	
	public static void main(String[] args) {
		for (int i = 1; i <= 2; i++) {
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					f1();
					f2();
				}
			});
			th.start();
		}
	}
}
