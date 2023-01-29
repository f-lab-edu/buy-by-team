package com.flab.bbt.deal.request;

import com.flab.bbt.deal.domain.Deal;
import com.flab.bbt.deal.domain.DealStatus;
import com.flab.bbt.product.domain.PriceTable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DealRequest {

    @NotNull(message = "제품 id는 필수 입력 값입니다.")
    private Long productId;

    @NotNull(message = "목표인원은 필수 입력 값입니다.")
    private int groupSize; // TODO("")

    @NotNull(message = "할인가격은 필수 입력 값입니다.")
    private int discountPrice; // TODO("")

    @NotNull(message = "딜 공개 여부는 필수 입력 값입니다.")
    private boolean isPrivate; // TODO("")

    public Deal converToEntity(PriceTable info) {
        return Deal.builder()
            .productId(info.getProductId())
            .groupSize(info.getDealCapacity())
            .discountPrice(info.getDiscountPrice())
            .status(DealStatus.CREATED)
            .participantCount(0)
            .expiredAt(LocalDateTime.now().plusHours(info.getDealValidPeriodInDays()))
            .isPrivate(info.isDealPrivate())
            .priceTableId(info.getId())
            .build();
    }
}
