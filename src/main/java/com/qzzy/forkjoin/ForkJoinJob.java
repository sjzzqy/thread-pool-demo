/**
 * @Copyright (c) https://gitee.com/Qzzy
 * @author Qzzy
 * @Generated: 2022/06/11 22:13:55
 */
package com.qzzy.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 
 * @author Qzzy
 * @Generated 2022/06/11 22:13
 */
public class ForkJoinJob extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;

	private int start;// 起始值
	private int end;// 结束值
	private int temp = 10;// 临界值,10个数字为一组，参与计算

	/**
	 * @param start
	 * @param end
	 */
	public ForkJoinJob(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		// TODO Auto-generated method stub

		if ((end - start) < temp) {
			int sum = 0;// 计算结果
			for (int i = start; i <= end; i++) {
				sum += i;
			}
			return sum;
		} else {
			int middle = (start + end) / 2;

			ForkJoinJob task1 = new ForkJoinJob(start, middle);
			// 向线程池中添加这个任务
			task1.fork();

			ForkJoinJob task2 = new ForkJoinJob(middle + 1, end);
			// 向线程池中添加这个任务
			task2.fork();

			return task1.join() + task2.join();
		}
	}

}
