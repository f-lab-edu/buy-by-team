package com.flab.bbt.deal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flab.bbt.exception.CustomException;
import com.flab.bbt.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Version;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Deal {

    private Long id;
    private Long productId;
    private int groupSize; // PriceTable 스냅샷. 목표인원
    private int discountPrice; // PriceTable 스냅샷. 할인가
    private DealStatus status; // TODO("이거 관련 처리도.... + mapper xml에서도 ")
    @Setter
    private int participantCount;
    private boolean isPrivate;
    private LocalDateTime expiredAt; // 마감되는 일시
    private LocalDateTime closedAt; // 성사된 일시
    private Integer version;

    @JsonProperty("isPrivate")
    public boolean getIsPrivate() {
        return this.isPrivate;
    }

    public Deal incrementParticipantCount(int count) {
        if (this.getParticipantCount() + count > this.getGroupSize()) {
            throw new CustomException(ErrorCode.DEAL_GROUP_SIZE_EXCEEDED);
        } else {
            this.setParticipantCount(this.getParticipantCount() + count);
            return this;
        }
    }
}
