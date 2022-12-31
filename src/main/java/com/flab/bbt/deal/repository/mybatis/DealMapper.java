package com.flab.bbt.deal.repository.mybatis;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.product.domain.Product;
import java.time.LocalDateTime;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DealMapper {
    int save(Deal deal);
    Optional<Deal> findById(Long id);
    int updateParticipantCountById(@Param("count")Integer updatedCount, @Param("id") Long id);
    Deal updateExpiredAtById(@Param("time") LocalDateTime expiredAt, @Param("id") Long id);
}
