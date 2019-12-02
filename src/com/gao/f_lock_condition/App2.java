package com.gao.f_lock_condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
 该例子演示了: ReetrantLock的阻塞是可中断的，synchronized的阻塞是不可中断的
*/
public class App2 {
	
	static class Service {
		private Lock lock = new ReentrantLock();
		public void serve() {
			// synchronized (this) {
				try {
					lock.lockInterruptibly(); // 必须使用lockInterruptibly方法，如果使用lock方法，则效果与synchronized一样
					System.out.println(Thread.currentThread().getName() + "获得锁");
					System.out.println(Thread.currentThread().getName() + "开始执行任务....");
					long startTime = System.currentTimeMillis();
					for (;;) {
						if (System.currentTimeMillis() - startTime > Integer.MAX_VALUE) {
							break;
						}
					}
					System.out.println(Thread.currentThread().getName() + "任务执行完毕....");
				} catch (Exception e) {
					System.out.println(e);
				} finally {
					System.out.println(Thread.currentThread().getName() + "释放锁");
					try {
						lock.unlock();
					} catch (Exception e) {
						// swollow exception
					}
				}
			// }
			System.out.println(Thread.currentThread().getName() + "继续去做别的事情");
		}
	}
	
	static class T implements Runnable {
		private Service service;
		public T(Service service) {
			this.service = service;
		}
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "开始执行");
			service.serve();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Service s = new Service();
		T t = new T(s);
		Thread th = new Thread(t, "郭靖");
		Thread th2 = new Thread(t,"黄蓉");
		th.start();
		TimeUnit.SECONDS.sleep(1);
		th2.start();
		
		System.in.read();
		th2.interrupt();
		System.out.println(Thread.currentThread().getName() + "中断" + th2.getName());
	}
}
