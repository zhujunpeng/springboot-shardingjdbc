package com.zjp.service;

import com.zjp.bean.Product;

import java.util.List;

/**
 * @Author: zhujunpeng
 * @Date: 2019/5/28 15:30
 * @Version 1.0
 */
public interface ProductService {

    /**
     * 添加商品
     * @param product
     */
    void addProduct(Product product);

    List<Product> getProduct();
}
