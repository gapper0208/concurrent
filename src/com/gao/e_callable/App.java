package com.gao.e_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 Callable & Future 的使用
*/
public class App {
	public static void main(String[] args) {
		ExecutorService es = Executors.newSingleThreadExecutor();
		Future<String> future = es.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "hello!";
			}
		});
		
		try {
			System.out.println("等待结果");
			String str = future.get();
			
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			es.shutdown();
		}
		
	}
	
}
