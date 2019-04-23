package cn.daniel.configuration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import cn.daniel.utils.DataSourceUtil;

@SpringBootConfiguration
@ConfigurationProperties(prefix = "jdbc")
public class DataSourceConfiguration {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, Map<String, String>> dbs;

    @PostConstruct
    public void init() {
        if (dbs.size() > 0) {
            Iterator<Map.Entry<String, Map<String, String>>> entries = dbs.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Map<String, String>> entry = entries.next();
                Map<String, String> config = entry.getValue();
                if (config == null || StringUtils.isEmpty(config.get("url"))
                        || StringUtils.isEmpty(config.get("username")) || StringUtils.isEmpty(config.get("password"))
                        || StringUtils.isEmpty(config.get("driverClassName"))
                        || !DataSourceUtil.isType(config.get("type"))) {
                    logger.error("数据源配置错误:" + entry.getKey());
                    entries.remove();
                }
            }
            logger.info("配置了" + dbs.size() + "个数据库信息" + dbs.keySet());
        } else {
            logger.info("没有配置数据库信息！！！");
        }
    }

    @Bean(name = "dataSource")
    @Primary // 优先使用，多数据源
    public DataSource dataSource() {
        AbstractRoutingDataSource dynamicDataSource = new AbstractRoutingDataSource() {

            @Override
            protected Object determineCurrentLookupKey() {
                String dbName = DataSourceUtil.getDataSource();
                if (dbName == null) {
                    dbName = DataSourceUtil.getDataSource();
                }
                logger.debug("数据源为：" + dbName);
                return dbName;
            }
        };
        // 配置多个数据源
        Map<Object, Object> map = new HashMap<Object, Object>();
        Iterator<Map.Entry<String, Map<String, String>>> entries = dbs.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Map<String, String>> entry = entries.next();
            String type = entry.getKey();
            Map<String, String> config = entry.getValue();
            DataSource dataSource = DataSourceUtil.getDataSource(config);
            map.put(entry.getKey(), dataSource);
            logger.info("添加数据库：" + entry.getKey());
            if ("main".equals(type)) {
                logger.info("设置默认数据源：" + entry.getKey());
                dynamicDataSource.setDefaultTargetDataSource(dataSource);
            }
        }
        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }

    public Map<String, Map<String, String>> getDbs() {
        return dbs;
    }

    public void setDbs(Map<String, Map<String, String>> dbs) {
        this.dbs = dbs;
    }
}
