package com.flab.bbt.product.repository;

import com.flab.bbt.product.domain.Product;
import java.util.Optional;

public interface ProductRepository{
    Product save(Product product);
    Optional<Product> findById(Long id);
    Optional<Product> findBySkuCode(String skuCode);
}