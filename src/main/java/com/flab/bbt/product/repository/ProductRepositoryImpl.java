package com.flab.bbt.product.repository;

import com.flab.bbt.product.domain.Product;
import com.flab.bbt.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    private static Map<Long, Product> productDb = new HashMap<>();
    private static Map<String, Long> serialNoIndex = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Product save(Product product) {
        product.setId(++sequence);
        productDb.put(product.getId(), product);
        serialNoIndex.put(product.getSerialNum(), product.getId());

        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productDb.get(id));
    }

    @Override
    public Optional<Product> findBySerialNum(String serialNum) {
        Long id = serialNoIndex.get(serialNum);
        return Optional.ofNullable(productDb.get(id));
    }
}
