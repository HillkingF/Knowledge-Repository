package com.nini.Transaction;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import javax.swing.*;

public class TestTransaction {
    public static void main(String[] args) {
        // 0、create data
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("name", "nini");
        String result = jsonObject.toJSONString();

        // 1、connect redis server
        Jedis jedis = new Jedis("localhost", 6379);

        // 2、start Transaction
        Transaction multi = jedis.multi();

        try {
            // 3、set data to transaction
            multi.set("user1", result);
            multi.set("user2", result);

            // 4、exec transaction
            multi.exec();
        } catch (Exception e) {
            // 5、discard
            multi.discard();
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            // 6、close connection
            jedis.close();

        }
    }
}
