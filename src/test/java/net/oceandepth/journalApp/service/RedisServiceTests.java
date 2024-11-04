package net.oceandepth.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisServiceTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testRedisConnection() {
        redisTemplate.opsForValue().set("age", "nineteen");
        Object age = redisTemplate.opsForValue().get("age");
        int a = 1;
    }

}