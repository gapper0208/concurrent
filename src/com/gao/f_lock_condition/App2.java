package com.gao.f_lock_condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
 ��������ʾ��: ReetrantLock�������ǿ��жϵģ�synchronized�������ǲ����жϵ�
*/
public class App2 {
	
	static class Service {
		private Lock lock = new ReentrantLock();
		public void serve() {
			// synchronized (this) {
				try {
					lock.lockInterruptibly(); // ����ʹ��lockInterruptibly���������ʹ��lock��������Ч����synchronizedһ��
					System.out.println(Thread.currentThread().getName() + "�����");
					System.out.println(Thread.currentThread().getName() + "��ʼִ������....");
					long startTime = System.currentTimeMillis();
					for (;;) {
						if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE) {
							break;
						}
					}
					System.out.println(Thread.currentThread().getName() + "����ִ�����....");
				} catch (Exception e) {
					System.out.println(e);
				} finally {
					System.out.println(Thread.currentThread().getName() + "�ͷ���");
					try {
						lock.unlock();
					} catch (Exception e) {
						// swollow exception
					}
				}
			// }
			System.out.println(Thread.currentThread().getName() + "����ȥ���������");
		}
	}
	
	static class T implements Runnable {
		private Service service;
		public T(Service service) {
			this.service = service;
		}
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "��ʼִ��");
			service.serve();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Service s = new Service();
		T t = new T(s);
		Thread th = new Thread(t, "����");
		Thread th2 = new Thread(t,"����");
		th.start();
		TimeUnit.SECONDS.sleep(1);
		th2.start();
		
		System.in.read();
		th2.interrupt();
		System.out.println(Thread.currentThread().getName() + "�ж�" + th2.getName());
	}
}
