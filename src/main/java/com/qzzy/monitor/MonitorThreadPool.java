/**
 * @Copyright (c) https://gitee.com/Qzzy
 * @author Qzzy
 * @Generated: 2022/06/11 22:54:35
 */
package com.qzzy.monitor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 监控线程池情况
 * 
 * @author Qzzy
 * @Generated 2022/06/11 22:54
 */
public class MonitorThreadPool extends ThreadPoolExecutor {

	/**
	 * 
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param unit
	 * @param workQueue
	 * @param threadFactory
	 * @param handler
	 */
	public MonitorThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
	}

	/**
	 * 每次任务执行前调用
	 */
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		monitor();
	}

	/**
	 * 每次任务完成后调用
	 */
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		monitor();
	}

	/**
	 * 线程池关闭前调用
	 */
	@Override
	protected void terminated() {
		monitor();
	}

	/**
	 * 监控线程池情况
	 */
	public void monitor() {

		System.out.print("正在工作的线程数：" + getActiveCount());
		System.out.print("当前存在的线程数：" + getPoolSize());
		System.out.print("历史最大的线程数：" + getLargestPoolSize());

		System.out.print("已提交的任务数：" + getTaskCount());
		System.out.print("已完成的任务数：" + getCompletedTaskCount());
		System.out.println("队列中的任务数：" + getQueue().size());
	}

}
