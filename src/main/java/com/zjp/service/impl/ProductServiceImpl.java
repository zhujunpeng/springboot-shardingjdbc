package com.zjp.service.impl;

import com.zjp.bean.Product;
import com.zjp.dao.ProductMapper;
import com.zjp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhujunpeng
 * @Date: 2019/5/28 15:32
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addProduct(Product product) {
        productMapper.insertSelective(product);
    }

    @Override
    public List<Product> getProduct() {
        return productMapper.selectAll();
    }
}
