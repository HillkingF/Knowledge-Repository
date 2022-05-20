package com.nini.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

public class ESTest_Index_Delete {
    public static void main(String[] args) throws IOException {
        // 创建ES客户端: 传入ip、port、http方式
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 删除索引
        DeleteIndexRequest request = new DeleteIndexRequest("user");   // 删除名字为user的索引
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);// Delete方法获取响应，其第二个参数是一个默认的参数，直接使用

        // 获取响应信息
        System.out.println(response.isAcknowledged());

        // 关闭es客户端
        restHighLevelClient.close();
    }
}
