package com.gao.f_lock_condition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/*
 	��Java��Lock�ӿڱ�synchronized���������ʲô������Ҫʵ��һ����Ч�Ļ��棬���������û�������ֻ����һ���û�д���Դ����������������ԣ��������ȥʵ������
*/
public class App6 {
	private Map<String, Object> cache = new HashMap<>();
	private static ReadWriteLock rwl = new ReentrantReadWriteLock();

	public Object getData(String key) {
		Object value = null;
		rwl.readLock().lock();
		try {
			value = cache.get(key);
			if (value == null) {
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try {
					if (value == null) {
						value = "foo";
						cache.put(key, value);
					}
				} finally {
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		} finally {
			rwl.writeLock().unlock();
		}
		return value;
	}

	public static void main(String[] args) {
	}
}
