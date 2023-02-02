package com.flab.bbt.product.request;

import com.flab.bbt.product.domain.PriceTable;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PriceTableRequest {

    @NotNull(message = "목표인원은 필수 입력 값입니다.")
    private int groupSize;

    @NotNull(message = "제품의 할인가는 필수 입력 값입니다.")
    private int priceDiscount;

    @NotNull(message = "제품의 정가는 필수 입력 값입니다.")
    private int priceSale;

    @NotNull(message = "제품의 할인율은 필수 입력 값입니다.")
    private int discountRate;

    @NotNull(message = "목표기간은 필수 입력 값입니다.")
    private int targetPeriod;

    public PriceTable convertToEntity(Long productId) {
        return PriceTable.builder()
            .productId(productId)
            .groupSize(this.groupSize)
            .targetPeriod(this.targetPeriod)
            .priceSale(this.priceSale)
            .priceDiscount(this.priceDiscount)
            .build();
    }
}
