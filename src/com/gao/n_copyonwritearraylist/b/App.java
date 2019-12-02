package com.gao.n_copyonwritearraylist.b;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// 该例子演示了普通的ArrayList不允许同时对集合中元素进行读写操作
// 而CopyOnWriteArrayList则可以

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
  优点:
  1.解决的开发工作中的多线程的并发问题。
  
  缺点:
  1.内存占有问题:很明显，两个数组同时驻扎在内存中，如果实际应用中，数据比较多，而且比较大的情况下，占用内存会比较大，
  针对这个其实可以用ConcurrentHashMap来代替。
  
  2.数据一致性:CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性。所以如果你希望写入的的数据，马上能读到，
  请不要使用CopyOnWrite容器
*/