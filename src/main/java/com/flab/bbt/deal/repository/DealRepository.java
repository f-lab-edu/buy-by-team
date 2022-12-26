package com.flab.bbt.deal.repository;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.product.domain.Product;
import java.util.Optional;

public interface DealRepository {
    Deal save(Deal deal);

    Optional<Deal> findById(Long id);
}
