package com.flab.bbt.deal.request;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.DealStatus;
import com.flab.bbt.product.domain.PriceTable;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.user.domain.User;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DealRequest {

    @NotNull(message = "제품 id는 필수 입력 값입니다.")
    private Long productId;

    @NotNull(message = "목표인원은 필수 입력 값입니다.")
    private int groupSize;

    @NotNull(message = "할인가격은 필수 입력 값입니다.")
    private int discountPrice;

    @NotNull(message = "딜 공개 여부는 필수 입력 값입니다.")
    private boolean isPrivate;

    public Deal converToEntity(Long productId) {
        return Deal.builder()
            .productId(productId)
            .groupSize(this.getGroupSize())
            .discountPrice(this.getDiscountPrice())
            .status(DealStatus.CREATED)
            .participantCount(0)
            .isPrivate(this.isPrivate)
            .build();
    }
}
