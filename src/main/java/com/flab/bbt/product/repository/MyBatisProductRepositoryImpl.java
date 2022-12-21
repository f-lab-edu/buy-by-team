package com.flab.bbt.product.repository;

import com.flab.bbt.product.domain.PriceTable;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.product.repository.mybatis.ProductMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisProductRepositoryImpl implements ProductRepository {

    private final ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        productMapper.save(product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productMapper.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public Optional<Product> findBySkuCode(String skuCode) {
        return productMapper.findBySkuCode(skuCode);
    }

    @Override
    public PriceTable savePriceTable(PriceTable priceTable) {
        productMapper.savePriceTable(priceTable);
        return priceTable;
    }
}
