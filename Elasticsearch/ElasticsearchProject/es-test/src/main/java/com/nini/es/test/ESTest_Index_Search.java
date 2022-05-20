package com.nini.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

public class ESTest_Index_Search {
    public static void main(String[] args) throws IOException {
        // 创建ES客户端: 传入ip、port、http方式
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 查询索引
        GetIndexRequest request = new GetIndexRequest("user");   // 获取名字为user的索引
        GetIndexResponse response = restHighLevelClient.indices().get(request, RequestOptions.DEFAULT);// get方法获取响应，其第二个参数是一个默认的参数，直接使用

        // 获取响应信息
        System.out.println(response.getAliases());
        System.out.println(response.getMappings());
        System.out.println(response.getSettings());


        // 关闭es客户端
        restHighLevelClient.close();
    }
}
