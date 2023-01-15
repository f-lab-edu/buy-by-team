package com.flab.bbt.deal.repository;

import com.flab.bbt.deal.domain.Deal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface DealRepository {

    Deal save(Deal deal);

    Optional<Deal> findById(Long id);

    Optional<Deal> findByIdForUpdate(Long id);

    Optional<Deal> update(Deal deal, int oldVersion, int newVersion);

    int updateParticipantCountById(int updatedCount, Long id);

    int updateExpiredAtById(LocalDateTime time, Long id);
}
