package com.nini.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ESTest_Doc_Delete {
    public static void main(String[] args) throws IOException {
        // 1.创建ES客户端: 传入ip、port、http方式
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 2.创建请求体(数据删除请求)
        DeleteRequest request = new DeleteRequest();
        request.index("user").id("1001"); // 指定需要删除数据的索引和id

        // 3、向ES删除数据并得到返回的响应信息，delete()方法
        DeleteResponse response = esClient.delete(request, RequestOptions.DEFAULT);

        // 4、此时可以查看返回的响应结果
        System.out.println(response.toString());

        // 5、最后关闭es客户端
        esClient.close();
    }
}
