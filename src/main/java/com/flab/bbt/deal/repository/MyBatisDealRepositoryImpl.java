package com.flab.bbt.deal.repository;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.DealStatus;
import com.flab.bbt.deal.domain.Participant;
import com.flab.bbt.deal.repository.mybatis.DealMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisDealRepositoryImpl implements DealRepository {

    private final DealMapper dealMapper;

    @Override
    public Deal createDeal(Deal deal) {
        deal.setStatus(DealStatus.CREATED);
        dealMapper.create(deal);
        return deal;
    }

    @Override
    public int updateParticipantCountById(int updatedCount, Long id) {
        return dealMapper.updateParticipantCountById(updatedCount, id);
    }

    @Override
    public int updateExpiredDeals() {
        return dealMapper.updateExpiredDeals(DealStatus.IN_PROGRESS, DealStatus.EXPIRED,
            LocalDateTime.now());
    }

    @Override
    public List<Deal> findByStatus(DealStatus status) {
        // TODO("이 부분은 DB 외에 캐시 활용도 가능할듯")
        return dealMapper.findByStatus(status);
    }

    @Override
    public Optional<Deal> findById(Long id) {
        return dealMapper.findById(id);
    }

    @Override
    public Optional<Deal> findByIdForUpdate(Long id) {
        return dealMapper.findByIdForUpdate(id);
    }

    @Override
    public int update(Deal deal) {
        return dealMapper.update(deal, deal.getVersion(), deal.getVersion() + 1);
    }

    @Override
    public Participant saveParticipant(Participant participant) {
        dealMapper.saveParticipant(participant);

        return participant;
    }

    @Override
    public Optional<Participant> findParticipantByDealIdAndUserId(Long dealId, Long userId) {
        return dealMapper.findParticipantByDealIdAndUserId(dealId, userId);
    }
}
