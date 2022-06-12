/**
 * @Copyright (c) https://gitee.com/Qzzy
 * @author Qzzy
 * @Generated: 2022/06/11 23:49:22
 */
package com.qzzy;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Qzzy
 * @Generated 2022/06/11 23:49
 */
@Service
@EnableAsync
public class EnableAsyncService {

	@Async
	public void mock() {
		try {
			Thread.sleep(6l);
			System.out.println("我是mock1");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Async
	public void mock2() {
		System.out.println("我是mock2");
	}
}
