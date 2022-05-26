package com.nini.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESTest_Doc_Update {
    public static void main(String[] args) throws IOException {
        // 1.创建ES客户端: 传入ip、port、http方式
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 2.创建请求体(数据更新请求)
        UpdateRequest request = new UpdateRequest();
        request.index("user").id("1001"); // 指定修改的索引和id
        // 3.在请求体中增加需要修改的数据,使用doc()方法
        request.doc(XContentType.JSON,"sex","女");

        // 4、向ES更新数据并得到返回的响应信息，更新的数据的必须是JSON格式
        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);

        // 5、此时可以查看返回的响应信息
        System.out.println(response.getResult());

        // 6、最后关闭es客户端
        esClient.close();
    }
}
