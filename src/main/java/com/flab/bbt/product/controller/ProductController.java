package com.flab.bbt.product.controller;

import com.flab.bbt.common.CommonResponse;
import com.flab.bbt.product.domain.PriceTable;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.product.request.PriceTableRequest;
import com.flab.bbt.product.request.ProductRequest;
import com.flab.bbt.product.response.ProductResponse;
import com.flab.bbt.product.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    /**
     * @param pageable page, size가 request param으로 넘어오면 Pageable 객체로 변환한다. 이때 custom
     *                 HandlerMethodArgumentResolver를 통해 param을 적절히 변환하여 반환한다.
     */
    @GetMapping
    public CommonResponse getProductList(Pageable pageable) {
        List<Product> products = productService.findProducts(pageable);

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
    public CommonResponse createPriceTable(@Valid @PathVariable long productId,
        @RequestBody PriceTableRequest request) {
        Product product = productService.findProductById(productId);
        PriceTable priceTable = productService.createPriceTable(
            request.convertToEntity(product.getId()));

        return CommonResponse.success(priceTable);
    }

}
