package com.flab.bbt.deal.service;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.repository.DealRepository;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
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
                // TODO("동시성 문제가 발생했을 경우 - 이것도 다른 Deal에 참여할 수 있게 하는 게 좋은지/가능할지")
            }
        } else {
            // TODO("정원 초과하더라도 다른 Deal에 참여할 수 있게 하는 로직 - 별도 이슈/PR로 빼겠습니다!")
        }
        return deal;
    }

    @Transactional
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
