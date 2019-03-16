package com.zjp.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 获取数据库表的列信息
 * @Author: zhujunpeng
 * @Date: 2018/12/19 16:52
 * @Version 1.0
 */
@Repository
public interface TableMapper {

    /**
     * 获取所有的表名
     * @return
     */
    @Select("select * from information_schema.TABLES where TABLE_SCHEMA=(select database())")
    List<Map> listTable();

    /**
     * 针对表获取列信息
     * @param tableName 表名
     * @return 返回表字段
     */
    @Select("select * from information_schema.COLUMNS where TABLE_SCHEMA = (select database()) and TABLE_NAME=#{tableName}")
    List<Map> listTableColumn(String tableName);

}
