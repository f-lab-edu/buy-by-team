package com.flab.bbt.product.repository;

import com.flab.bbt.product.domain.Product;
import com.flab.bbt.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static Map<Long, Product> productDb = new ConcurrentHashMap<>();
    private static Map<String, Long> serialNoIndex = new HashMap<>();
    private static AtomicLong sequence = new AtomicLong(0);

    @Override
    public Product save(Product product) {
        product.setId(sequence.incrementAndGet());
        if (productDb.putIfAbsent(product.getId(), product) == null) {
            serialNoIndex.put(product.getSerialNum(), product.getId());
        }
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productDb.get(id));
    }

    @Override
    public Optional<Product> findBySerialNum(String serialNum) {
        Long id = serialNoIndex.get(serialNum);
        if (id != null) {
            return Optional.of(productDb.get(id));
        } else {
            return Optional.empty();
        }
    }
}
