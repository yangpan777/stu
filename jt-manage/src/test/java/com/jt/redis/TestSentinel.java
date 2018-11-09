package com.jt.redis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class TestSentinel {
	@Test
	public void testSentinel(){
		//IP:端口 
		Set<String> sentinels =new HashSet<>(); 
		sentinels.add("192.168.161.138:26379");
		JedisSentinelPool pool = new JedisSentinelPool("mymaster",sentinels);
		Jedis jedis = pool.getResource();
		System.out.println(pool);
		jedis.set("shaobing", "哨兵测试");
		System.out.println(jedis.get("shaobing"));
		pool.returnResourceObject(jedis);
	}
}
