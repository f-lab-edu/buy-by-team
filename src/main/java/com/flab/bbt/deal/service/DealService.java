package com.flab.bbt.deal.service;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.DealStatus;
import com.flab.bbt.deal.repository.DealRepository;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DealService {

    private final DealRepository dealRepository;

    public Deal createDeal(Deal deal) {
        return dealRepository.createDeal(deal);
    }

    public Deal incrementParticipantCount(Long dealId, int count) {
        Deal deal = findDealByIdForUpdate(dealId);
        if (deal.isJoinable(count)) {
            deal.incrementParticipantCount(count);
            int updatedRows = dealRepository.update(deal);
            if (updatedRows == 0) {
                Deal dealInProgress = dealRepository.findByStatus(DealStatus.IN_PROGRESS).get(0);
                incrementParticipantCount(dealInProgress.getId(), count);
            }
        } else {
            Deal dealInProgress = dealRepository.findByStatus(DealStatus.IN_PROGRESS).get(0);
            incrementParticipantCount(dealInProgress.getId(), count);
        }
        return deal;
    }

    @Transactional
    public Deal incrementParticipantCount(Long dealId) {
        return incrementParticipantCount(dealId, 1);
    }

    @Scheduled(cron = "0 0/30 0 * * ?")
    public int expireDeals() {
        return dealRepository.updateExpiredDeals();
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
