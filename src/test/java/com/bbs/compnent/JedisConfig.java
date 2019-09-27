package com.bbs.compnent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xue.bbs.common.JedisClient;
import com.xue.bbs.common.JedisClientPool;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {
	
	
	@Bean
	public JedisClient jedisClientPool() {
		JedisClientPool clientPool = new JedisClientPool();
		clientPool.setJedisPool(jedisPool());
		return clientPool;
	}

	@Bean
	public JedisPool jedisPool() {
		//new JedisPool(poolConfig, host, port, timeout, password);
		return new JedisPool();
	}
	
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		return new JedisPoolConfig();
	}
	
}
