package com.flab.bbt.deal.repository;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.repository.mybatis.DealMapper;
import com.flab.bbt.product.domain.Product;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisDealRepositoryImpl implements DealRepository {

    private final DealMapper dealMapper;
    @Override
    public Deal save(Deal deal) {
        dealMapper.save(deal);
        return deal;
    }

    @Override
    public Optional<Deal> findById(Long id) {
        return dealMapper.findById(id);
    }
}
