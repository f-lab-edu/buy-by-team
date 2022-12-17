package com.flab.bbt.deal.request;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.DealStatus;
import com.flab.bbt.product.domain.Product;
import com.flab.bbt.user.domain.User;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DealRequest {
    @NotNull(message = "제품 ID는 필수 입력 값입니다.")
    private Long productId;
    @NotNull(message = "목표인원은 필수 입력 값입니다.")
    private int targetNum;
    @NotNull(message = "딜 공개 여부는 필수 입력 값입니다.")
    private boolean isPrivate;
    @NotBlank(message = "목표기간은 필수 입력 값입니다.")
    private String targetPeriod;

    public Deal converToEntity(User user, Product product){
        return Deal.builder()
            .owner(user)
            .product(product)
            .targetNum(this.targetNum)
            .participantNum(0)
            .dealStatus(DealStatus.CREATED)
            .isPrivate(this.isPrivate)
            .targetPeriod(this.targetPeriod)
            .build();
    }
}
