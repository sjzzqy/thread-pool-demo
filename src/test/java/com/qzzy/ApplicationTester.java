/**
 * @Copyright (c) https://gitee.com/Qzzy
 * @author Qzzy
 * @Generated: 2022/06/11 16:51:49
 */
package com.qzzy;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest(IndexController.class)
public class ApplicationTester {
	
	@Autowired
	private EnableAsyncService service;

	@Test
	public void test() {
		service.mock();
		service.mock2();
	}

//	@Autowired
//	private MockMvc mvc;
//
//	@Test
//	public void mockController() throws Exception {
//		// edit访问路径
//		// param 传入参数
//		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/edit").param("name", "张三").param("age", "10"))
//				.andReturn();
//		MockHttpServletResponse response = result.getResponse();
//		String content = response.getContentAsString();
//		System.out.println(content);
//	}
}
