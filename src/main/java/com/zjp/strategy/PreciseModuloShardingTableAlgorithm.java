package com.zjp.strategy;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 表分库规则
 * @Author: zhujunpeng
 * @Date: 2019/5/28 14:17
 * @Version 1.0
 */
public class PreciseModuloShardingTableAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        String tb_name = preciseShardingValue.getLogicTableName();
        try {
            String value = preciseShardingValue.getValue();
            tb_name = tb_name + (value.hashCode() % 2);
            System.out.println("tb_name:" + tb_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String each : collection) {
            System.out.println("tb:" + each);
            if (each.equals(tb_name)) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }
}
