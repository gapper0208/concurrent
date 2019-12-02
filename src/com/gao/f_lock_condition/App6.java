package com.gao.f_lock_condition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/*
 	在Java中Lock接口比synchronized块的优势是什么？你需要实现一个高效的缓存，它允许多个用户读，但只允许一个用户写，以此来保持它的完整性，你会怎样去实现它？
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
