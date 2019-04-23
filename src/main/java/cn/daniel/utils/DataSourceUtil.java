package cn.daniel.utils;

import java.beans.PropertyVetoException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtil {

    private static Logger logger = LoggerFactory.getLogger(DataSourceUtil.class);

    private DataSourceUtil() {
    }

    private static final Set<String> datasourceTypes;
    static {
        datasourceTypes = new HashSet<>();
        datasourceTypes.add("druid");
        datasourceTypes.add("c3p0");
        datasourceTypes.add("dbcp");
    }

    public static boolean isType(String type) {
        return datasourceTypes.contains(type);
    }

    public static DataSource getDataSource(Map<String, String> params) {
        switch (params.get("type")) {
        case "druid":
            return getDruidDataSource(params);
        case "c3p0":
            return getC3p0DataSource(params);
        case "dbcp":
            return getDbcpDataSource(params);
        default:
            return getJdbcDataSource(params);
        }
    }

    /**
     * 默认配置jdbc连接池
     * 
     * @param params
     * @return
     */
    public static DataSource getJdbcDataSource(Map<String, String> params) {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl(params.get("url"));
        datasource.setUsername(params.get("username"));
        datasource.setPassword(params.get("password"));
        datasource.setDriverClassName(params.get("driverClassName"));
        return datasource;
    }

    /**
     * 默认配置c3p0连接池
     * 
     * @param params
     * @return
     */
    public static DataSource getC3p0DataSource(Map<String, String> params) {
        ComboPooledDataSource datasource = new ComboPooledDataSource();
        try {
            datasource.setJdbcUrl(params.get("url"));
            datasource.setUser(params.get("username"));
            datasource.setPassword(params.get("password"));
            datasource.setDriverClass(params.get("driverClassName"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    /**
     * 默认配置dbcp连接池
     * 
     * @param params
     * @return
     */
    public static DataSource getDbcpDataSource(Map<String, String> params) {
        BasicDataSource datasource = new BasicDataSource();
        datasource.setUrl(params.get("url"));
        datasource.setUsername(params.get("username"));
        datasource.setPassword(params.get("password"));
        datasource.setDriverClassName(params.get("driverClassName"));
        return datasource;
    }

    /**
     * 默认配置druid连接池
     * 
     * @param params
     * @return
     */
    public static DataSource getDruidDataSource(Map<String, String> params) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(params.get("url"));
        datasource.setUsername(params.get("username"));
        datasource.setPassword(params.get("password"));
        datasource.setDriverClassName(params.get("driverClassName"));
        return datasource;
    }

    private final static ThreadLocal<String> local = new ThreadLocal<String>();

    public static void putDataSource(String name) {
        logger.debug("存入数据源");
        local.set(name);
    }

    public static String getDataSource() {
        logger.debug("获取数据源");
        return local.get();
    }
}