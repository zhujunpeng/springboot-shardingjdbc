package com.zjp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 1、去除数据库自动配置
 * 2、开启事务
 * 3、加入配置实体
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties
@MapperScan(basePackages = "com.zjp.dao")
public class SpringbootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisApplication.class, args);
    }
}
