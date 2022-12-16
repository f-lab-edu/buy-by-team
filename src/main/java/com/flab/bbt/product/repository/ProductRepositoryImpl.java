package com.flab.bbt.product.repository;

import com.flab.bbt.common.Pageable;
import com.flab.bbt.product.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepositoryImpl implements ProductRepository {

    private static Map<Long, Product> productDb = new ConcurrentHashMap<>();
    private static Map<String, Long> skuCodeIndex = new HashMap<>();
    private static AtomicLong sequence = new AtomicLong(0);

    @Override
    public Product save(Product product) {
        product.setId(sequence.incrementAndGet());
        if (productDb.putIfAbsent(product.getId(), product) == null) {
            skuCodeIndex.put(product.getSkuCode(), product.getId());
        }
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productDb.get(id));
    }

    @Override
    public Optional<Product> findBySkuCode(String skuCode) {
        Long id = skuCodeIndex.get(skuCode);
        if (id != null) {
            return Optional.of(productDb.get(id));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findListWithPagination(Pageable pageable) {
        return new ArrayList<>(productDb.values());
    }
}
