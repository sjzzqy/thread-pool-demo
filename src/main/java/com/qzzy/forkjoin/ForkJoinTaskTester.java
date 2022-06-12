/**
 * @Copyright (c) https://gitee.com/Qzzy
 * @author Qzzy
 * @Generated: 2022/06/11 22:22:40
 */
package com.qzzy.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * 
 * @author Qzzy
 * @Generated 2022/06/11 22:22
 */
public class ForkJoinTaskTester {

	public static void main(String[] args) {

		ForkJoinJob task = new ForkJoinJob(1, 5);
		ForkJoinPool pool = new ForkJoinPool();
		try {
			
			
			ForkJoinTask<Integer> submit = pool.submit(task);
			System.out.println(submit.get());
			
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}

	}

}
