/**
 * @Copyright (c) https://gitee.com/Qzzy
 * @author Qzzy
 * @Generated: 2022/06/11 20:19:27
 */
package com.qzzy.threadpool;

/**
 * 
 * @author Qzzy
 * @Generated 2022/06/11 20:19
 */
public class ThreadPoolJob implements Runnable{

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName());
		
	}

}
