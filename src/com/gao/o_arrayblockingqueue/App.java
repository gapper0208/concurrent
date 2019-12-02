package com.gao.o_arrayblockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// ArrayBlockingQueue���������е�һ��ʵ���࣬�ײ������飬
// �ü��ϵ�add��put��offer���������뼯�������Ԫ�أ������в�ͬ������

//put�����ڶ�������ʱ�������ֱ���ж��г�Ա�����ѣ�take�����ڶ��пյ�ʱ���������ֱ���ж��г�Ա���Ž�����

public class App {
	public static void main(String[] args)  {
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
		for(int i = 1; i <=5 ; i++) {
			// add������������ʱ�����׳��쳣
//			 System.out.println("add method: " + queue.add("value" + i) );
			
			// put������������ʱ��������
//			try {
//				queue.put("value" + i);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println("put method:" + i);
			
//			offer������������ʱ�����������������ǻ���������false����ȻҲ�����Ԫ�ؼ��뼯���С�
			System.out.println("offer method:" + queue.offer("value" + i));

			// offer����һ��������ʽ���ڳ�����������ʱ����������һ��ʱ�䣬
			// �������ʱ���������п�λ�ڳ��Ļ����ͻ����Ԫ�سɹ���
			// ���������ʱ���򷵻�false
//			try {
//				System.out.println("offer method:" + queue.offer("value" + i, 1, TimeUnit.SECONDS));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		
//		for(int i = 1; i <= 5; i ++) {
//			try {
//				System.out.println("take:" + queue.take());
//				System.out.println("take:" + queue.poll());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		System.out.println("over");
	}
}