package com.flab.bbt.deal.repository.mybatis;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.DealStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DealMapper {

    int create(Deal deal);

    int updateParticipantCountById(@Param("count") Integer updatedCount, @Param("id") Long id);

    int updateExpiredDeals(@Param("beforeStatus") DealStatus beforeStatus,
        @Param("afterStatus") DealStatus afterStatus,
        @Param("time") LocalDateTime time);

    int update(Deal deal, int oldVersion, int newVersion);

    Optional<Deal> findById(Long id);

    Optional<Deal> findByIdForUpdate(Long id);

    List<Deal> findByStatus(DealStatus status);

}
