package com.flab.bbt.product.domain;

import com.flab.bbt.deal.domain.Deal;
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
    private Long productId; // 제품의 ID
    private int priceSale; // 제품의 정가
    private int priceDiscount; // 제품의 할인가
    private int targetPeriod; // 제품에 대한 팀구매의 만료기한(단위: day)


    private int dealCapacity; // 목표인원
    private int discountPrice;
    private boolean isDealPrivate;

    private int dealValidPeriodInDays; // 마감기한 계산을 위한 필드. 예를 들어 1day일 경우 1.
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Deal convertToDealEntity() {
        return Deal.builder()
            .productId(this.getProductId())
            .groupSize(this.getDealCapacity())
            .discountPrice(this.getDiscountPrice())
            .expiredAt(LocalDateTime.now().plusHours(this.getDealValidPeriodInDays()))
            .isPrivate(this.isDealPrivate())
            .priceTableId(this.getId())
            .build();
    }


    public int getDiscountRate() {
        return (priceSale - priceDiscount) / priceSale;
    }
}
