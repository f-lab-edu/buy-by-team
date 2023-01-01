package com.flab.bbt.product.domain;

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
    private int groupSize; // 목표인원
    private int discountPrice;
    private int targetPeriod; // 마감기한 계산을 위한 필드. 예를 들어 1day일 경우 1.
}
