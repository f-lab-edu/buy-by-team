package com.flab.bbt.product.repository;

import com.flab.bbt.product.domain.Product;
import com.flab.bbt.user.domain.User;

import java.util.Optional;

public interface ProductRepository{
    Product save(Product product);
    Optional<Product> findById(Long id);
    Optional<Product> findBySerialNum(String serialNum);
}