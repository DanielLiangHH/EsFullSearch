package cn.daniel.configuration;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@AutoConfigureAfter(MybatisConfiguration.class) // 保证在MyBatisConfig实例化之后再实例化该类
public class MapperScanConfiguration {

    // mapper接口的扫描器
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("cn.daniel.mapper");
        return mapperScannerConfigurer;
    }
}
