package com.flab.bbt.product.repository;

import com.flab.bbt.product.domain.PriceTable;
import com.flab.bbt.product.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository{
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    Optional<Product> findBySkuCode(String skuCode);

    PriceTable savePriceTable(PriceTable priceTable);
}