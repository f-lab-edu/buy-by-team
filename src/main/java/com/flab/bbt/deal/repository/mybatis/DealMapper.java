package com.flab.bbt.deal.repository.mybatis;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.DealStatus;
import java.time.LocalDateTime;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DealMapper {

    int save(Deal deal);

    int updateParticipantCountById(@Param("count") Integer updatedCount, @Param("id") Long id);

    int updateExpiredDeals(@Param("beforeStatus") DealStatus beforeStatus,
        @Param("afterStatus") DealStatus afterStatus,
        @Param("time") LocalDateTime time);

    int update(Deal deal);

    Optional<Deal> findById(Long id);

    Optional<Deal> findByIdForUpdate(Long id);


}
