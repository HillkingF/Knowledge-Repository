package com.nini.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ESTest_Doc_Query {
    public static void main(String[] args) throws IOException {
        // 1.创建ES客户端: 传入ip、port、http方式
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 2.创建请求体(数据查询请求)
        GetRequest request = new GetRequest();
        request.index("user").id("1001"); // 指定查询的索引和id

        // 3、向ES查询数据并得到返回的响应信息
        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);

        // 4、此时可以查看返回的响应信息
        System.out.println(response.getSourceAsString());

        // 5、最后关闭es客户端
        esClient.close();
    }
}
