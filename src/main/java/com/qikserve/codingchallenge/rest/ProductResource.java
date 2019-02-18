package com.qikserve.codingchallenge.rest;

import com.qikserve.codingchallenge.API;
import com.qikserve.codingchallenge.entity.ProductInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Xiaoming Wang
 */
@RestController
@RequestMapping("api/products")
public class ProductResource {

    @GetMapping
    public List<ProductInfo> getAllProducts(){
        return API.getAllProducts();
    }
}
