package com.flab.bbt.product.service;

import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void register(Product product){
        productRepository.findBySerialNum(product.getSerialNum()).ifPresent(
                p -> {throw new CustomException(ErrorCode.PRODUCT_ALREADY_EXISTS); }
        );
        productRepository.save(product);
    }

    public Product findProductById(long id){
        return productRepository.findById(id)
                .orElseThrow(()-> {return new CustomException(ErrorCode.PRODUCT_NOT_FOUND);});
    }
}
