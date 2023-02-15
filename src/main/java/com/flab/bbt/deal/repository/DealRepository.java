package com.flab.bbt.deal.repository;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.DealStatus;
import java.util.List;
import java.util.Optional;

public interface DealRepository {

    Deal createDeal(Deal deal);

    Optional<Deal> findById(Long id);

    Optional<Deal> findByIdForUpdate(Long id);

    int update(Deal deal);

    int updateParticipantCountById(int updatedCount, Long id);

    int updateExpiredDeals();

    List<Deal> findByStatus(DealStatus status);
}
