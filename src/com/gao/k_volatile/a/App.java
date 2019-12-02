package com.gao.k_volatile.a;

import java.util.concurrent.TimeUnit;

// ������������volatile�����εı��������̵߳Ŀɼ���
// ע�⣬�ڲ�ʹ��volatile����r�£���whileѭ���д�ӡb��Ҳ��ǿ���߳�ȥ���ڴ���ˢ��b��ֵ��

public class App {
	/* volatile */ boolean b = true;
	
	void f1() {
		System.out.println("start");
		while(b){}
		System.out.println("end");
	}
	
	public static void main(String[] args) {
		App app = new App();
		new Thread(new Runnable() {
			@Override
			public void run() {
				app.f1();
			}
		}).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		app.b = false;
		System.out.println("set b to false..");
	}
}