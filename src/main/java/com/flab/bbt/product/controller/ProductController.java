package com.flab.bbt.product.controller;

import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.product.repository.ProductRepository;
import com.flab.bbt.product.request.ProductRequest;
import com.flab.bbt.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public CommonResponse getProductList() {
        // get list of products
        return CommonResponse.success();
    }

    @GetMapping("/{id}")
    public CommonResponse getProduct(@PathVariable long id) {
        Product product = productService.findProductById(id);
        return CommonResponse.success(product);
    }

    @PostMapping()
    public CommonResponse addProduct(@Valid @RequestBody ProductRequest request){
        Product product = request.convertToEntity(request);
        productService.register(product);
        return CommonResponse.success();
    }

}
