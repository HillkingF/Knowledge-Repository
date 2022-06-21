package com.nini.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class ESTest_Doc_Query_filter {
    public static void main(String[] args) throws IOException {
        // 1.创建ES客户端: 传入ip、port、http方式
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 2.创建请求体(数据查询请求)
        SearchRequest request = new SearchRequest();
        request.indices("user"); // 指定查询的索引

        // 3.构造查询条件(先查询索引中全部的数据，然后再进行字段的显示过滤)
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .query(QueryBuilders.matchAllQuery()); //查询全部的数据
        String[] excludes = {};        // 结果中不需要包含的字段组
        String[] includes = {"name"};  // 结果中需要包含name字段
        builder.fetchSource(includes, excludes);     // 这个方法可以有若干个参数，查看源码：includes表示包含, excludes表示排除
        request.source(builder);   // source()方法中的参数是查询条件

        // 4、向ES查询数据并得到返回的响应信息（search方法）
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        // 5、此时可以查看返回的响应信息
        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits()); //查看查询条数
        System.out.println(response.getTook());  //查看查询时间
        for(SearchHit hit: hits){                //查看每一条数据
            System.out.println(hit.getSourceAsString());
        }

        // 6、最后关闭es客户端
        esClient.close();
    }
}