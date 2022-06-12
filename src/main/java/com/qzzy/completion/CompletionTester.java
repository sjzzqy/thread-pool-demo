/**
 * @Copyright (c) https://gitee.com/Qzzy
 * @author Qzzy
 * @Generated: 2022/06/11 22:36:36
 */
package com.qzzy.completion;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * CompletionService,按任务完成时间先后顺序返回执行结果； “执行优先”原则，先执行先返回
 * 
 * @author Qzzy
 * @Generated 2022/06/11 22:36
 */
public class CompletionTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService threadPool = Executors.newFixedThreadPool(5);

		try {
			ExecutorCompletionService<Integer> executorService = new ExecutorCompletionService<Integer>(threadPool);

			// 提交5个任务
			for (int i = 5; i >= 1; i--) {
				CompletionJob job = new CompletionJob(i);
				executorService.submit(job);
			}

			for (int i = 0; i < 5; i++) {
				Future<Integer> take = executorService.take();//take阻塞式获取返回结果
				Integer integer = take.get();

				System.out.println("返回顺序：" + integer);
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}

	}

}
