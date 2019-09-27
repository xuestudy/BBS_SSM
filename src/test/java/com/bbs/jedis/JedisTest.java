package com.bbs.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xue.bbs.common.JedisClient;

public class JedisTest {
	@SuppressWarnings("resource")
	@Test
	public void jedisTest() {
		//初始化Spirng容器
		ApplicationContext applicationContext = new 
				ClassPathXmlApplicationContext("classpath:spring/RedisContext.xml");
		
		//从容器中得到JedisPool对象
		JedisClient clientPool = applicationContext.getBean(JedisClient.class);
		
		//set key value
		clientPool.set("phone", "15079833056");
		
		//get key
		System.out.println(clientPool.get("phone"));
	}

}
