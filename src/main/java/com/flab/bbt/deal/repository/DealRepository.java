package com.flab.bbt.deal.repository;

import com.flab.bbt.deal.domain.Deal;

public interface DealRepository {
    Deal save(Deal deal);
}
