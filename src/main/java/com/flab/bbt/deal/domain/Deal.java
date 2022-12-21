package com.flab.bbt.deal.domain;

import com.flab.bbt.product.domain.Product;
import com.flab.bbt.user.domain.User;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Deal {
    private Long id;
    private Long productId;
    private int targetNum; // PriceTable 스냅샷. 목표인원
    private int discountPrice; // PriceTable 스냅샷. 할인가
    private int targetPeriod; // PriceTable 스냅샷. 마감기한 계산을 위한 필드
    private DealStatus dealStatus;
    private int participantCount;
    private boolean isPrivate;
    private LocalDateTime expiredAt;
}
