package com.zjp.controller;

import com.zjp.bean.Product;
import com.zjp.bean.User;
import com.zjp.service.ProductService;
import com.zjp.utils.PageResultSet;
import com.zjp.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zhujunpeng
 * @Date: 2019/5/28 15:33
 * @Version 1.0
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public Result getAllUser(){
        List<Product> product = productService.getProduct();
        return Result.success(product);
    }

    /**
     * 添加商品
     * @param product
     * @return
     */
    @PostMapping("/add")
    public Result addUser( @RequestBody Product product){
        productService.addProduct(product);
        return Result.success();
    }
}
