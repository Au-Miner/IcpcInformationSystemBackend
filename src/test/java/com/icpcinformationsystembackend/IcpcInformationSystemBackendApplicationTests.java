package com.icpcinformationsystembackend;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

// @SpringBootTest
class IcpcInformationSystemBackendApplicationTests {
    @Test
    void contextLoads() {
        Jedis jedis = new Jedis("124.222.109.34", 6379);
        //测试
        String ping = jedis.ping();
        System.out.println(ping);
    }
}
