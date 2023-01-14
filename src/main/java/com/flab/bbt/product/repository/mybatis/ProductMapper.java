package com.flab.bbt.product.repository.mybatis;

import com.flab.bbt.product.domain.PriceTable;
import com.flab.bbt.product.domain.Product;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

@Mapper
public interface ProductMapper {

    int save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findListWithPagination(Pageable pageable);

    Optional<Product> findBySkuCode(String skuCode);

    int savePriceTable(PriceTable priceTable);

    int delete(Long id);
}
