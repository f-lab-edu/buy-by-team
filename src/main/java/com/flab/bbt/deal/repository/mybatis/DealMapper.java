package com.flab.bbt.deal.repository.mybatis;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.product.domain.Product;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DealMapper {

    int save(Deal deal);
    
    Optional<Deal> findById(Long id);

}
