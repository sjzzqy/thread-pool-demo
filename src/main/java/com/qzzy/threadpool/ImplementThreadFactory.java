/**
 * @Copyright (c) https://gitee.com/Qzzy
 * @author Qzzy
 * @Generated: 2022/06/11 20:05:46
 */
package com.qzzy.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author Qzzy
 * @Generated 2022/06/11 20:05
 */
public class ImplementThreadFactory implements ThreadFactory {

	/**
	 * 线程计数器，原子，不存在线程安全问题
	 */
	private final AtomicInteger i = new AtomicInteger(1);

	@Override
	public Thread newThread(Runnable r) {
//		Executors.defaultThreadFactory();
		Thread thread = new Thread(r);
		thread.setName("订单线程" + i.getAndIncrement() + "号");
		return thread;
	}
}
