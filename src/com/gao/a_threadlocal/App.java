package com.gao.a_threadlocal;

import java.util.Random;

// f1()����������������飬 f2()���������ȡf1()���������������
public class App {
	static int n;
	static void f1() {
		n = new Random().nextInt();
		System.out.println("f1����:" + n);
	}
	static void f2() {
		System.out.println("f2��ȡ:" + n);
	}	
	public static void main(String[] args) {
		f1();
		f2();
	}
}