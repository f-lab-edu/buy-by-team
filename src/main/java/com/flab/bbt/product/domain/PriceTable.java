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
    private Long productId; // 제품의 ID
    private int groupSize; // 제품에 대한 팀구매의 목표인원
    private int priceSale; // 제품의 정가
    private int priceDiscount; // 제품의 할인가
    private int discountRate; // 제품의 할인율
    private int targetPeriod; // 제품에 대한 팀구매의 만료기한(단위: day)
}
