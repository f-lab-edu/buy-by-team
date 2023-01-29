package com.flab.bbt.product.service;

import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.product.domain.PriceTable;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.product.repository.ProductRepository;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void register(Product product) {
        productRepository.findBySkuCode(product.getSkuCode()).ifPresent(
            p -> {
                throw new CustomException(ErrorCode.PRODUCT_ALREADY_EXISTS);
            }
        );
        productRepository.save(product);
    }

    public Product findProductById(long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    public List<Product> findProducts(Pageable pageable) {
        return productRepository.findListWithPagination(pageable);
    }

    public PriceTable createPriceTable(PriceTable priceTable) {
        return productRepository.savePriceTable(priceTable);
    }

    public PriceTable findPriceTableByProductId(long productId) {
        return productRepository.findPriceTableByProductId(productId)
            .orElseThrow(() -> new CustomException(ErrorCode.DEAL_NOT_FOUND));
    }
}
