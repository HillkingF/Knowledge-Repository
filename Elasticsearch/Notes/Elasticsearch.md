# Elasticsearch

教程参考尚硅谷：https://www.gulixueyuan.com/goods/show/534

版本：7.8.0

es官网：https://www.elastic.co/cn/elasticsearch/

es文档：https://www.elastic.co/guide/en/elasticsearch/reference/7.8/index.html

这里先学习es7.8，之后再来看最新版本的变化

## 第1章 Elasticsearch概述

### 1.1 什么是ES

Elastic Stack（也称作ELK Stack）包括 ES、Kibana、Beats和Logstash。能够安全可靠的获取各种来源和格式的数据，然后进行实时的数据搜索、分析和可视化。



Elasticsearch（ES）是一个开源的高扩展的**分布式全文搜索引擎**，是Elastic Stack技术栈的核心。



ES的特点：

- 可以几乎实时的存储、检索数据
- 扩展性好，可以扩展到上百台服务器，处理PB级别的数据



### 1.2 全文搜索引擎

这里的全文搜索引擎指的是**目前广泛应用的主流搜索引擎**。它的工作原理是计算机索引程序通过**扫描文章中的每一个词，对每一个词建立一个索引，指明该词在文章中出现的次数和位置，当用户查询时，检索程序就根据事先建立的索引进行查找，并将查找的结果反馈给用户的检索方式**。这个过程类似于通过字典中的检索字表查字的过程。





### 1.3 Elasticsearch和Solr

在Java环境中**Lucene**是一个免费的开源工具。Lucene提供了一个简单强大的应用程式接口，能够做全文索引和搜寻。Lucene是近几年最受欢迎的免费Java信息检索程序库。

但**Lucene**只是一个提供全文搜索功能类库的**核心工具包**，真正使用它还需要搭建一个完善的服务框架进行应用。



目前市面上流行的搜索引擎软件，主流的就是：Elasticsearch和Solr，这两款都是基于Lucene搭建的，可以独立部署启动的搜索引擎服务软件。两者内核相同，因此服务器安装、部署、管理、集群类似，对数据的操作、修改、添加、保存、查询等都类似。



在使用时会将两者进行对比选型，各有优缺点。







## 第2章 Elasticsearch入门

### 2.1 Elasticsearch安装

#### 2.1.1 下载软件

ES的官方地址：https://www.elastic.co/cn/

ES最新的版本是8.2(截止2022.5.7)

下载地址：https://www.elastic.co/cn/downloads/elasticsearch

====================================================================

选择7.8.0版本，macos下载得到一个安装包，解压如下：

<img src="/Users/hillking/MyDocuments/GitHub/Knowledge-Repository/Elasticsearch/Notes/img/%E6%88%AA%E5%B1%8F2022-05-07%2012.32.42.png" style="zoom:50%;" />

#### 2.1.2 启动软件

在`bin`目录下：

- `elasticsearch`文件使用终端打开启动es

  ```bash
  启动完毕后会显示两个端口号：
  9300端口为ES集群间组件的通信端口
  9200端口为浏览器访问的http协议RESTful端口
  #[2022-05-07T12:34:38,198][INFO ][o.e.t.TransportService   ] [fengwennideMacBook-Pro.local] publish_address {127.0.0.1:9300}, bound_addresses {[::1]:9300}, {127.0.0.1:9300}
  #[2022-05-07T12:34:42,301][INFO ][o.e.h.AbstractHttpServerTransport] [fengwennideMacBook-Pro.local] publish_address {127.0.0.1:9200}, bound_addresses {[::1]:9200}, {127.0.0.1:9200}
  
  
  在浏览器中输入：http://localhost:9200/
  如果页面中显示如下内容，则说明es启动成功：
  {
    "name" : "fengwennideMacBook-Pro.local",
    "cluster_name" : "elasticsearch",
    "cluster_uuid" : "ko_1QUxFQOebO7KGgNhLpg",
    "version" : {
      "number" : "7.8.0",
      "build_flavor" : "default",
      "build_type" : "tar",
      "build_hash" : "757314695644ea9a1dc2fecd26d1a43856725e65",
      "build_date" : "2020-06-14T19:35:50.234439Z",
      "build_snapshot" : false,
      "lucene_version" : "8.5.1",
      "minimum_wire_compatibility_version" : "6.8.0",
      "minimum_index_compatibility_version" : "6.0.0-beta1"
    },
    "tagline" : "You Know, for Search"
  }
  ```



#### 2.1.3 安装启动常见问题

- ES是用java开发的，且7.8版本的ES需要JDK1.8以上，如果系统配置了默认的JDK那么使用系统的JDK，如果没有配置使用安装包中自带的JDK。

- 双击启动窗口闪退，通过路径访问追踪错误，如果是“空间不足”，可以修改`config/jvm.options`配置文件

  ![](/Users/hillking/MyDocuments/GitHub/Knowledge-Repository/Elasticsearch/Notes/img/%E6%88%AA%E5%B1%8F2022-05-07%2013.44.17.png)



### 2.2 客户端安装

如果直接通过浏览器向Elasticsearch服务器发送请求，那么需要在发送的**请求中包含HTTP标准的方法**。

而HTTP的大部分特性仅支持**GET**和**POST**方法。

所以为了能方便的进行客户端访问，可以使用**Postman**软件。



Posman是一款网页调试工具，提供强大的WebAPI和HTTP请求调试功能。中文版能发送任何类型的HTTP请求（GET，POST，PUT，DELETE等）。不仅能提交表单，还能附带任意类型的请求体。



**Postman官网**：https://www.postman.com/downloads/



Elasticsearch环境



Elasticsearch进阶



Elasticsearch集成



Elasticsearch优化



Elasticsearch面试题





