package com.zjp.controller;

import com.zjp.dao.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 获取表信息
 * @Author: zhujunpeng
 * @Date: 2018/12/19 16:58
 * @Version 1.0
 */
@RestController
@RequestMapping("table")
public class TableController {

    @Autowired
    private TableMapper tableMapper;

    @RequestMapping("/list")
    public Object list(String id) {
        List<Map> maps = tableMapper.listTable();
        return maps;
    }
    @RequestMapping("/columns")
    public Object info(String tableName) {
        List<Map> maps = tableMapper.listTableColumn(tableName);
        return maps;
    }

}
