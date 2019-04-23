package cn.daniel.configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@SpringBootConfiguration
@AutoConfigureAfter(DataSourceConfiguration.class)
public class MybatisConfiguration {

    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Bean
    @ConditionalOnMissingBean // 当容器里没有指定的Bean的情况下创建该对象
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设置mybatis的主配置文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        org.springframework.core.io.Resource mybatisConfigXml = resolver
                .getResource("classpath:mybatis/mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(mybatisConfigXml);
        // 设置别名包
        sqlSessionFactoryBean.setTypeAliasesPackage("cn.daniel.createIndex.bean");
        return sqlSessionFactoryBean;
    }
}
