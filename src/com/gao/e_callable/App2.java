package com.gao.e_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 	CompletionService一次性执行多个Callable
 	就像有10块地，哪块先熟就收哪块!
*/
public class App2 {
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		CompletionService<String> cs = new ExecutorCompletionService<>(es);
		for (int i = 1; i <= 10; i++) {
			final int task = i;
			cs.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					Thread.sleep((long) (Math.random() * 5000));
					return "任务" + task + "完成";
				}
			});
		}
		
		for (int i = 1; i <= 10; i++) {
			try {
				Future<String> f = cs.take();
				System.out.println(f.get());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		es.shutdown();
		
	}
}
