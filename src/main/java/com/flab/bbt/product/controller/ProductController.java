package com.flab.bbt.product.controller;

import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.product.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public CommonResponse getProductList() {
        // get list of products
        return CommonResponse.success();
    }

    @GetMapping("/{id}")
    public CommonResponse getProduct(@PathVariable long id) {
        // get product detail for Product(id)
        return CommonResponse.success();
    }
}
