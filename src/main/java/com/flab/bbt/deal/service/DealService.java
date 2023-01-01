package com.flab.bbt.deal.service;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.repository.DealRepository;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DealService {

    private final DealRepository dealRepository;

    public Deal createDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    public int incrementParticipantCount(Deal deal, int count) {
        deal.setParticipantCount(deal.getParticipantCount() + count);
        if (deal.getParticipantCount() <= deal.getGroupSize()) {
            dealRepository.updateParticipantCountById(deal.getParticipantCount(), deal.getId());
            return deal.getId().intValue();
        } else {
            return -1;
        }

    }

    public int incrementParticipantCount(Deal deal) {
        return incrementParticipantCount(deal, 1);
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
}
