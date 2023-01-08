package com.flab.bbt.deal.service;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.repository.DealRepository;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DealService {

    private final DealRepository dealRepository;

    public Deal createDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Deal incrementParticipantCount(Long dealId, int count) {
        Deal deal = findDealByIdForUpdate(dealId);
        deal.incrementParticipantCount(count);
        return dealRepository.update(deal);
    }

    public Deal incrementParticipantCount(Long dealId) {
        return incrementParticipantCount(dealId, 1);
    }

    public int expireDeal(Deal deal, LocalDateTime expiredAt) {
        return dealRepository.updateExpiredAtById(expiredAt, deal.getId());
    }

    public int expireDeal(Deal deal) {
        return expireDeal(deal, LocalDateTime.now());
    }

    public Deal findDealById(long id) {
        return dealRepository.findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.DEAL_NOT_FOUND));
    }

    public Deal findDealByIdForUpdate(long id) {
        return dealRepository.findByIdForUpdate(id)
            .orElseThrow(() -> new CustomException(ErrorCode.DEAL_NOT_FOUND));
    }
}
