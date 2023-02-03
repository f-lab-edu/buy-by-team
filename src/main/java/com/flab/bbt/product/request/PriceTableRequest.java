package com.flab.bbt.product.request;

import com.flab.bbt.product.domain.PriceTable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PriceTableRequest {

    @NotNull(message = "목표인원은 필수 입력 값입니다.")
    private int dealCapacity;

    @NotNull(message = "제품의 할인가는 필수 입력 값입니다.")
    private int priceDiscount;

    @NotNull(message = "제품의 정가는 필수 입력 값입니다.")
    private int priceSale;
    
    @NotNull(message = "목표기간은 필수 입력 값입니다.")
    private int dealValidPeriodInDays;

    public PriceTable convertToEntity(Long productId) {
        return PriceTable.builder()
            .productId(productId)
            .targetPeriod(this.targetPeriod)
            .priceSale(this.priceSale)
            .priceDiscount(this.priceDiscount)
            .dealCapacity(this.dealCapacity)
            .dealValidPeriodInDays(this.dealValidPeriodInDays)
            .build();
    }
}
