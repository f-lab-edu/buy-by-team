package com.flab.bbt.deal.service;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.repository.DealRepository;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import com.flab.bbt.product.domain.PriceTable;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DealService {

    private final DealRepository dealRepository;

    public Deal createDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    public Deal incrementParticipantCount(Long dealId, int count) {
        Deal deal = findDealByIdForUpdate(dealId);
        deal.incrementParticipantCount(count);
        Deal updatedDeal = dealRepository.update(deal);
        return updatedDeal;
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

    // 이게 DealRepository에 있는 게 맞는지 / ProductRepository에 있는 게 맞을지 / DealInfoRepository라는 것을 만들어야 할지
    public PriceTable findPriceTableByProductId(long productId) {
        return dealRepository.findPriceTableByProductId(productId)
            .orElseThrow(() -> new CustomException(ErrorCode.DEAL_NOT_FOUND));
    }

}
