package com.nini;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nini.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        // 在企业开发中，80%情况下都不会使用这种原生的方式去编写原生代码。  ==>  RedisUtils

        // redisTemplate  操作不同的数据类型，api方法和Redis指令一样
        // opsForValue  操作字符串 类型String
        // opsForList   操作List  类型String
        // opsForSet
        // opsForHash
        // opsForZSet
        // opsForGeo
        // opsForHyperLoglog

        // 除了基本的操作，常用的方法都可以直接redisTemplate操作，比如事务、基本的CRUD
        //		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        //		connection.flushDb();
        //		connection.flushAll();

        redisTemplate.opsForValue().set("mykey", "nini");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

    @Test
    public void test() throws JsonProcessingException {
        // 真实的开发中一般都使用json来传递对象
        User user = new User("nini", 3);
        // 因此将user对象保存成一个json字符串
        // String jsonUser = new ObjectMapper().writeValueAsString(user);
        // 然后将json字符串放到redis中：使用set存储，使用get查看
        redisTemplate.opsForValue().set("user1", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }
}
