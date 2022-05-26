package com.nini.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESTest_Doc_Insert {
    public static void main(String[] args) throws IOException {
        // 1.创建ES客户端: 传入ip、port、http方式
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 2.创建请求体
        IndexRequest request = new IndexRequest();
        request.index("user").id("1001"); // 使用index()方法选择需要插入的索引以及id

        // 3.创建需要插入的数据
        User user = new User();
        user.setName("nini");
        user.setAge(30);
        user.setSex("男");

        // 4.将对象格式的数据转换成JSON字符串
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);

        // 5.将JSON字符串数据放入请求体中
        request.source(userJson, XContentType.JSON); //两个参数分别是：Json数据和数据类型


        // 6、向ES插入数据并得到返回的响应信息，插入的必须是JSON格式数据
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);

        // 7、此时可以查看返回的响应信息
        System.out.println(response.getResult());

        // 8、最后关闭es客户端
        esClient.close();
    }
}
