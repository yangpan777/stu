package com.jt.redis;

import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestRedis {
	//测试String类型   IP:端口
	/**报错原因
	 * 1.检查防火墙是否关闭
	 * 2.检查IP绑定是否注释了  默认绑定本机(Linux)  #bing ip
	 * 3.保护模式是否关闭  默认值yes  改后no  修改后重启
	 * 4.启动方式不对。。。。。 redis-server redis.conf
	 */
	@Test
	public void testString(){
		Jedis jedis = new Jedis("192.168.161.138",6379);
		jedis.set("1806", "星期一，新的一周开始");
		System.out.println("获取redis内容:"+jedis.get("1806"));
	}
	
	//测试hash值
	@Test
	public void testHash(){
		Jedis jedis = new Jedis("192.168.161.138",6379);
		Long start = System.currentTimeMillis();
		jedis.hset("user", "name", "potato");
		jedis.hset("user", "age", "18");
		jedis.hset("user", "gender", "women");
		Long end = System.currentTimeMillis();
		System.out.println(end-start+"毫秒");
		Map<String, String> abc = jedis.hgetAll("user");
		System.out.println(abc);
	}
	
	@Test
	public void testList(){
		Jedis jedis = new Jedis("192.168.161.138",6379);
		jedis.lpush("list", "1","2","3","4","5");
		Long a = jedis.llen("list");
		for(int i=0;i<a;i++){
			System.out.println("左边的数据为:"+jedis.rpop("list"));
		}
	}
	
	@Test
	public void testTx(){
		Jedis jedis = new Jedis("192.168.161.138",6379);;
		//1.开启事务控制
		Transaction transaction = jedis.multi();
		
		//2.执行入库操作
		transaction.set("a", "事务测试");
		transaction.set("b", "bb");
		
		//3.事务提交
		transaction.exec();
		
		//4.事务回滚
		transaction.discard();
		System.out.println(jedis.get("a"));
		
	}
}
