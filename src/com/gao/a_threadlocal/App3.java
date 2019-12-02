package com.gao.a_threadlocal;

import java.util.Random;

/*
	���������̣߳�ִ����ͬ������f1()�������ݣ� f2()��ȡ���ݡ�
	���������⣺��������£�2���̲߳��������־�Ȼ����һ���ģ� ����������һ�ָ��ʣ��� ��������ǲ����ܵģ���
	
	eg. 
	Thread-1 f1����:99318067
	Thread-0 f1����:99318067
	Thread-1 f2��ȡ:99318067
	Thread-0 f2��ȡ:99318067
	
	��Ҳ����룬��f1���ɵ�����������������ݸ�f2����������������  Ҫ֪�����ܶ�ʱ�򣬷������������Լ����ģ�
	���Ǳ���ʱ�������ʹ�ñ����Ѿ��涨�õķ�������û�а취ȥ�޸ķ����Ĳ����ģ�  �����Ҫʹ��"�̱߳��ر�����"��
 */


public class App3 {
	static int n;
	static void f1() {
		n = new Random().nextInt();
		System.out.println(Thread.currentThread().getName() + " f1����:" + n);
	}
	static void f2() {
		System.out.println(Thread.currentThread().getName() + " f2��ȡ:" + n);
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
