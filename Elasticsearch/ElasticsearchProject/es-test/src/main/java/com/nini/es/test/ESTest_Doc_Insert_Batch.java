package com.nini.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESTest_Doc_Insert_Batch {
    public static void main(String[] args) throws IOException {
        // 1.创建ES客户端: 传入ip、port、http方式
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 2.创建批量操作请求体
        BulkRequest request = new BulkRequest();

        // 3.使用add()方法向请求体中添加多条数据
        request.add(new IndexRequest().index("user").id("1001").source(XContentType.JSON,"name","张三") );
        request.add(new IndexRequest().index("user").id("1002").source(XContentType.JSON,"name","李四") );
        request.add(new IndexRequest().index("user").id("1003").source(XContentType.JSON,"name","王五") );

        // 4.将包含多条数据的请求体放入es客户端，使用bulk()方法进行批量传递
        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);

        // 5.查看返回的响应信息
        System.out.println(response.getTook());  //查看时间消耗
        System.out.println(response.getItems());

        // 6、最后关闭es客户端
        esClient.close();
    }
}
