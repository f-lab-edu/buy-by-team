package com.flab.bbt.deal.repository.mybatis;

import com.flab.bbt.deal.domain.Deal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DealMapper {

    int save(Deal deal);

    int updateParticipantCountById(@Param("count") Integer updatedCount, @Param("id") Long id);

    int updateExpiredAtById(@Param("time") LocalDateTime expiredAt, @Param("id") Long id);

    Optional<Deal> update(Deal deal, int oldVersion, int newVersion);

    Optional<Deal> findById(Long id);

    Optional<Deal> findByIdForUpdate(Long id);

}
