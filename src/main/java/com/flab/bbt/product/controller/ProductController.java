package com.flab.bbt.product.controller;

import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.product.domain.PriceTable;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.product.repository.ProductRepository;
import com.flab.bbt.product.request.PriceTableRequest;
import com.flab.bbt.product.request.ProductRequest;
import com.flab.bbt.product.response.ProductResponse;
import com.flab.bbt.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public CommonResponse getProductList() {
        List<Product> products = productService.findProducts();

        return CommonResponse.success(products.stream()
                .map(ProductResponse::convertToProductResponse)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public CommonResponse getProduct(@PathVariable long id) {
        Product product = productService.findProductById(id);
        return CommonResponse.success(product);
    }

    @PostMapping()
    public CommonResponse addProduct(@Valid @RequestBody ProductRequest request) {
        Product product = request.convertToEntity(request);
        productService.register(product);
        return CommonResponse.success();
    }

    @PostMapping("/{productId}/price-tables")
    public CommonResponse createPriceTable(@Valid @PathVariable long productId, @RequestBody PriceTableRequest request) {
        Product product = productService.findProductById(productId);
        PriceTable priceTable = productService.createPriceTable(request.convertToEntity(product));

        return CommonResponse.success(priceTable);
    }

}
