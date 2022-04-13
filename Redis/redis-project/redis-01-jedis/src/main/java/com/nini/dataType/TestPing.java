package com.nini.dataType;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        // 1.new Jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2. 使用ping来测试一下是否成功。如果成功会返回Pong
        System.out.println(jedis.ping());
        // 3.jedis所有的方法就是我们之前学习的所有指令，两者名字基本一样。这里不再过多尝试


    }
}
