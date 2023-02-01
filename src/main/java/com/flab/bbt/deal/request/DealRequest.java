package com.flab.bbt.deal.request;
import com.flab.bbt.deal.domain.Deal;
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

    public Deal convertToEntity(Long productId) {
        return Deal.builder()
            .productId(productId)
            .groupSize(this.getGroupSize())
            .discountPrice(this.getDiscountPrice())
            .isPrivate(this.isPrivate)
            .build();
    }
}
