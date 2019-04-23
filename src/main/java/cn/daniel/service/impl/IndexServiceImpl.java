package cn.daniel.service.impl;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.daniel.service.IndexService;

public class IndexServiceImpl implements IndexService {

    @Autowired
    private TransportClient transportClient;

    public void createIndex(IndexConf indexConf, Map<String, Object> index) {
        transportClient.prepareIndex(indexConf.getIndexName(), indexConf.getTypeName(), indexConf.getIndexId())
                .setSource(index).get();
        transportClient.prepareIndex(indexConf.getIndexName(), indexConf.getTypeName(), indexConf.getIndexId())
                .setSource(index).execute().actionGet();
    }

    /**
     * 创建索引
     */
    public void buildindex() {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder().startObject().startObject("sina")
                    .startObject("properties").startObject("article_title").field("type", "text").field("store", true)
                    .field("index", true).endObject().startObject("article_content").field("type", "text")
                    .field("store", true).field("index", true).endObject().startObject("article_url")
                    .field("type", "text").field("store", true).field("index", true).endObject().endObject().endObject()
                    .endObject();
            transportClient.admin().indices().prepareCreate("pages").execute().actionGet();
            PutMappingRequest mapping = Requests.putMappingRequest("pages").type("sina").source(builder);
            transportClient.admin().indices().putMapping(mapping).actionGet();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    class IndexConf {

        private String indexName;

        private String typeName;

        private String indexId;

        private String clusterName;

        public String getIndexName() {
            return indexName;
        }

        public void setIndexName(String indexName) {
            this.indexName = indexName;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getIndexId() {
            return indexId;
        }

        public void setIndexId(String indexId) {
            this.indexId = indexId;
        }

        public String getClusterName() {
            return clusterName;
        }

        public void setClusterName(String clusterName) {
            this.clusterName = clusterName;
        }
    }
}
