package com.jt.redis;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestShardsRedis {
	@Test
	public void test01() {
		//准备List封装多台redis
		List<JedisShardInfo> shards=new ArrayList<>();
		shards.add(new JedisShardInfo("192.168.50.134",6379));
		shards.add(new JedisShardInfo("192.168.50.134",6380));
		shards.add(new JedisShardInfo("192.168.50.134",6381));
		ShardedJedis jedis=new ShardedJedis(shards);
		//当前操作是redis 对象
		jedis.set("1812","分片测试");
		System.out.println(jedis.get("1812"));
	}
	
	//测试spring整合redis分片
		@Autowired
		private ShardedJedis jedis;
		
		@Test
		public void test02() {
		
			jedis.set("1812", "spring整合成功!!!!!");
			System.out.println(jedis.get("1812"));
		}

}
