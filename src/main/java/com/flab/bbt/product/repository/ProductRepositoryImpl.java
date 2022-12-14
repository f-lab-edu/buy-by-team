package com.flab.bbt.product.repository;

import com.flab.bbt.product.domain.PriceTable;
import com.flab.bbt.product.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.data.domain.Pageable;

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
    public PriceTable savePriceTable(PriceTable priceTable) {
        return null;
    }

    @Override
    public List<Product> findListWithPagination(Pageable pageable) {
        return new ArrayList<>(productDb.values());
    }
}
