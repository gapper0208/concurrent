package com.gao.a_threadlocal;

import java.util.Random;

// f1()方法负责生成随机书， f2()方法负责获取f1()所产生的随机数。
public class App {
	static int n;
	static void f1() {
		n = new Random().nextInt();
		System.out.println("f1产生:" + n);
	}
	static void f2() {
		System.out.println("f2获取:" + n);
	}	
	public static void main(String[] args) {
		f1();
		f2();
	}
}