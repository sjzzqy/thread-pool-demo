/**
 * @Copyright (c) https://gitee.com/Qzzy
 * @author Qzzy
 * @Generated: 2022/06/11 22:39:39
 */
package com.qzzy.completion;

import java.util.concurrent.Callable;

/**
 * 
 * @author Qzzy
 * @Generated 2022/06/11 22:39
 */
public class CompletionJob implements Callable<Integer> {

	private int timeout;// 执行时间

	/**
	 * @param timeout
	 */
	public CompletionJob(int timeout) {
		super();
		this.timeout = timeout;
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("执行顺序：" + timeout);

		Thread.sleep(timeout * 100L);

		return timeout;
	}

}
