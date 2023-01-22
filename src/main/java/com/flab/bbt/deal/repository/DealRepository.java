package com.flab.bbt.deal.repository;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.DealInfo;
import java.time.LocalDateTime;
import java.util.Optional;

public interface DealRepository {

    Deal save(Deal deal);

    Optional<Deal> findById(Long id);

    Optional<Deal> findByIdForUpdate(Long id);

    Deal update(Deal deal);

    int updateParticipantCountById(int updatedCount, Long id);

    int updateExpiredAtById(LocalDateTime time, Long id);

    Optional<DealInfo> findDealInfoByProductId(Long productId);
}
