package com.flab.bbt.deal.repository;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.Participant;
import java.time.LocalDateTime;
import java.util.Optional;

public interface DealRepository {

    Deal createDeal(Deal deal);

    Optional<Deal> findById(Long id);

    Optional<Deal> findByIdForUpdate(Long id);

    int update(Deal deal);

    int updateParticipantCountById(int updatedCount, Long id);

    int updateExpiredDeals();

    Participant saveParticipant(Participant participant);

    Optional<Participant> findParticipantByDealIdAndUserId(Long dealId, Long userId);
}
