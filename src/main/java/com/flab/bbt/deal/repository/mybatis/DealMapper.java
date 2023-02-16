package com.flab.bbt.deal.repository.mybatis;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.DealStatus;
import com.flab.bbt.deal.domain.Participant;
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

    int update(@Param("deal") Deal deal, @Param("oldVersion") int oldVersion,
        @Param("newVersion") int newVersion);

    Optional<Deal> findById(Long id);

    Optional<Deal> findByIdForUpdate(Long id);

    int saveParticipant(Participant participant);

    List<Deal> findByStatus(DealStatus status);

    Optional<Participant> findParticipantByDealIdAndUserId(@Param("dealId") Long dealId,
        @Param("userId") Long userId);
}
