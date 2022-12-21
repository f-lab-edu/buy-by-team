package com.flab.bbt.product.request;

import com.flab.bbt.product.domain.PriceTable;
import com.flab.bbt.product.domain.Product;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PriceTableRequest {
    @NotNull(message = "목표인원은 필수 입력 값입니다.")
    private int targetNum;

    @NotNull(message = "할인금액은 필수 입력 값입니다.")
    private int discountPrice;

    @NotNull(message = "목표기간은 필수 입력 값입니다.")
    private int targetPeriod;

    public PriceTable convertToEntity(Product product) {
        return PriceTable.builder()
            .productId(product.getId())
            .targetNum(this.targetNum)
            .discountPrice(this.discountPrice)
            .targetPeriod(this.targetPeriod)
            .build();
    }
}
