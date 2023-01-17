package com.flab.bbt.deal.repository;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.repository.mybatis.DealMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MyBatisDealRepositoryImpl implements DealRepository {

    private final DealMapper dealMapper;

    @Override
    public Deal save(Deal deal) {
        dealMapper.save(deal);
        return deal;
    }

    @Override
    public int updateParticipantCountById(int updatedCount, Long id) {
        return dealMapper.updateParticipantCountById(updatedCount, id);
    }

    @Override
    public int updateExpiredAtById(LocalDateTime time, Long id) {
        return dealMapper.updateExpiredAtById(time, id);
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
}
