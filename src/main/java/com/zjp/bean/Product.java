package com.zjp.bean;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 商品
 * @Author: zhujunpeng
 * @Date: 2019/5/28 15:28
 * @Version 1.0
 */
@Data
@Table(name = "t_product")
@NameStyle(Style.camelhumpAndLowercase)
public class Product implements Serializable {

    @Id
    // 申明字段与数据库的对应
    @Column(name = "id")
    // 根据主键查询或者更新一定要加上主键策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    private String name;
    private Integer store;
    private Double price;
}
