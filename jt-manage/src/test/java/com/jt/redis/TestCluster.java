package com.jt.redis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class TestCluster {
	@Test
	public void testCluster(){
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.161.138",7000));
		nodes.add(new HostAndPort("192.168.161.138",7001));
		nodes.add(new HostAndPort("192.168.161.138",7002));
		nodes.add(new HostAndPort("192.168.161.138",7003));
		nodes.add(new HostAndPort("192.168.161.138",7004));
		nodes.add(new HostAndPort("192.168.161.138",7005));
		nodes.add(new HostAndPort("192.168.161.138",7006));
		nodes.add(new HostAndPort("192.168.161.138",7007));
		nodes.add(new HostAndPort("192.168.161.138",7008));

		JedisCluster jedisCluster = new JedisCluster(nodes);

		jedisCluster.set("a", "挂号费丢活动覅USD回复的说法");
		System.out.println(jedisCluster.get("a"));
		jedisCluster.close();


	}
}
