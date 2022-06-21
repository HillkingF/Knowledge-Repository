package com.nini.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;

public class ESTest_Doc_Query_group {

    private static HighlightBuilder highlightBuilder;

    public static void main(String[] args) throws IOException {
        // 1.创建ES客户端: 传入ip、port、http方式
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        // 2.创建请求体(数据查询请求)
        SearchRequest request = new SearchRequest();
        request.indices("user"); // 指定查询的索引

        // 3.构造分组查询条件并放入构造器中. terms("ageGroup")定义分组查询结果名字. field("age")说明需要分组的字段;
        SearchSourceBuilder builder = new SearchSourceBuilder();
        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
        builder.aggregation(aggregationBuilder);               // 将聚合查询条件放入构造器中

        // 4.将查询条件放入请求体中进行查询
        request.source(builder);   // source()方法中的参数是查询条件
        // 5、向ES查询数据并得到返回的响应信息（search方法）
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        // 6、此时可以查看返回的响应信息
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
