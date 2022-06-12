/**
 * @Copyright (c) httThreadPoolExecutorps://gitee.com/Qzzy
 * @author Qzzy
 * @Generated: 2022/06/11 19:53:01
 */
package com.qzzy.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.qzzy.monitor.MonitorThreadPool;

/**
 * 
 * @author Qzzy
 * @Generated 2022/06/11 19:53
 */
public class ThreadPoolTester {

	public static void main(String[] args) {
		// 创建任务
		ThreadPoolJob task1 = new ThreadPoolJob();
		ThreadPoolJob task2 = new ThreadPoolJob();
		ThreadPoolJob task3 = new ThreadPoolJob();

		ThreadPoolJobResult resultTask = new ThreadPoolJobResult();

//		ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(4);

		/** 核心线程2个，最大线程4，存活时间10秒，存活时间10秒， */
		MonitorThreadPool threadPoolExecutor = new MonitorThreadPool(2, 4, 10L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(), new ImplementThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

		// 提交任务
//		threadPoolExecutor.execute(task1);
//		threadPoolExecutor.execute(task2);
//		threadPoolExecutor.execute(task3);

		try {
			// public Future<?> submit(Runnable task)
			Future<?> submit = threadPoolExecutor.submit(task1);
			System.out.println(submit.isDone());

			// public <T> Future<T> submit(Runnable task, T result)
			Future<String> submit2 = threadPoolExecutor.submit(task2, "我是附带参数");
			System.out.println(submit2.get());

			// public <T> Future<T> submit(Callable<T> task)
			Future<Integer> resultFuture = threadPoolExecutor.submit(resultTask);
			Integer result = resultFuture.get();
			System.out.println(result);

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			// 关闭线程池
			threadPoolExecutor.shutdown();
		}

	}

}
