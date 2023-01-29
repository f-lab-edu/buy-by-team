package com.flab.bbt.product.domain;

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
public class PriceTable {

    private Long id;
    private Long productId;
    private int dealCapacity; // 목표인원
    private int discountPrice;
    private boolean isDealPrivate;

    private int dealValidPeriodInDays; // 마감기한 계산을 위한 필드. 예를 들어 1day일 경우 1.
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
