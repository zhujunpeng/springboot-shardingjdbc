package com.zjp.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zjp.strategy.PreciseModuloShardingTableAlgorithm;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhujunpeng
 * @Date: 2019/5/28 14:49
 * @Version 1.0
 */
@Configuration
public class ShardingConfiguration {

    @Bean
    public DataSource getDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getOrderItemTableRuleConfiguration());
//        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
        shardingRuleConfig.getBindingTableGroups().add("t_user");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
        // 分表规则
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("name", new PreciseModuloShardingTableAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new Properties());
    }

    /**
     * 分表规则
     * @return
     */
    private static TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_user", "ds${0..1}.t_user${0..1}");
        // 生成id规则
//        result.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "order_id", new Properties()));
        return result;
    }

    /**
     * 分表规则
     * @return
     */
    private static TableRuleConfiguration getOrderItemTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_order_item", "ds${0..1}.t_order_item${0..1}");
        // 生成id规则
//        result.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "order_item_id", new Properties()));
        return result;
    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();

        result.put("ds0", createDataSource("jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false"));
        result.put("ds1", createDataSource("jdbc:mysql://localhost:3306/test2?characterEncoding=utf8&useSSL=false"));
        return result;
    }

    private DataSource createDataSource(final String dataSourceName) {
        //使用默认连接池
        DruidDataSource result = new DruidDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        //设置数据库路径
        result.setUrl(dataSourceName);
        //设置数据库用户名
        result.setUsername("root");
        //设置数据密码
        result.setPassword("root");
        return result;
    }

}
