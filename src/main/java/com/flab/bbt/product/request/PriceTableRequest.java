package com.flab.bbt.product.request;

import com.flab.bbt.product.domain.PriceTable;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PriceTableRequest {

    @NotNull(message = "목표인원은 필수 입력 값입니다.")
    private int groupSize;

    @NotNull(message = "할인금액은 필수 입력 값입니다.")
    private int discountPrice;

    @NotNull(message = "목표기간은 필수 입력 값입니다.")
    private int targetPeriod;

    public PriceTable convertToEntity(Long productId) {
        return PriceTable.builder()
            .productId(productId)
            .dealCapacity(this.groupSize)
            .discountPrice(this.discountPrice)
            .dealValidPeriodInDays(this.targetPeriod)
            .build();
    }
}
