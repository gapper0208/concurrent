package com.gao.n_copyonwritearraylist.b;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// ��������ʾ����ͨ��ArrayList������ͬʱ�Լ�����Ԫ�ؽ��ж�д����
// ��CopyOnWriteArrayList�����

public class App {
	public static void main(String[] args) {
//		List<String> list = new ArrayList<>(Arrays.asList("1 2 3 4 5".split(" ")));
		List<String> list = new CopyOnWriteArrayList<>(Arrays.asList("1 2 3 4 5".split(" ")));
		for (String str : list) {
			System.out.println(str);
			list.add("x");
		}
		
		System.out.println(list);
	}
}


/*
  �ŵ�:
  1.����Ŀ��������еĶ��̵߳Ĳ������⡣
  
  ȱ��:
  1.�ڴ�ռ������:�����ԣ���������ͬʱפ�����ڴ��У����ʵ��Ӧ���У����ݱȽ϶࣬���ұȽϴ������£�ռ���ڴ��Ƚϴ�
  ��������ʵ������ConcurrentHashMap�����档
  
  2.����һ����:CopyOnWrite����ֻ�ܱ�֤���ݵ�����һ���ԣ����ܱ�֤���ݵ�ʵʱһ���ԡ����������ϣ��д��ĵ����ݣ������ܶ�����
  �벻Ҫʹ��CopyOnWrite����
*/