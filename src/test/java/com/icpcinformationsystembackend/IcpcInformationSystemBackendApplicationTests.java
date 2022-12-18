package com.icpcinformationsystembackend;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

// @SpringBootTest
class IcpcInformationSystemBackendApplicationTests {
    @Resource
    private StringRedisTemplate redisTemplate;

    @Test
    void test() {
    }
}
