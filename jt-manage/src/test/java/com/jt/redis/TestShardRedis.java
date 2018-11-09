package com.jt.redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


public class TestShardRedis {

		@Test
		public void testShard(){
			
			/**
			 * 创建分片对象
			 * 1.poolConfig  标识池的大小
			 * 2.shards redis 分片的节点信息
			 */
			
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxTotal(1000);
			poolConfig.setTestOnBorrow(true);//获取链接时先检测
			
			List<JedisShardInfo> shards = new ArrayList<>();
			shards.add(new JedisShardInfo("192.168.161.138",6379));
			shards.add(new JedisShardInfo("192.168.161.138",6380));
			shards.add(new JedisShardInfo("192.168.161.138",6381));
			
			ShardedJedisPool pool = new ShardedJedisPool(poolConfig, shards);
			//获取redis链接
			ShardedJedis jedis = pool.getResource();
			jedis.set("shards", "顶呱呱毒杀高大上的");
			System.out.println(jedis.get("shards"));
			
			//将链接还回连接池中
			pool.returnResource(jedis);
		}
}
