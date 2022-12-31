package com.flab.bbt.deal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Deal {

    private Long id;
    private Long productId;
    private int groupSize; // PriceTable 스냅샷. 목표인원
    private int discountPrice; // PriceTable 스냅샷. 할인가
    private DealStatus status;
    @Setter
    private int participantCount;
    private boolean isPrivate;
    private LocalDateTime expiredAt; // 마감되는 일시
    private LocalDateTime closedAt; // 성사된 일시

    @JsonProperty("isPrivate")
    public boolean getIsPrivate() {
        return this.isPrivate;
    }
}
