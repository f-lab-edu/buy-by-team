package com.flab.bbt.order.request;

import com.flab.bbt.order.domain.Order;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderRequest {

    @NotNull(message = "Deal Id는 필수 입력 값입니다.")
    private Long dealId;

    @NotNull(message = "User Id는 필수 입력 값입니다.")
    private long userId;

    @NotNull(message = "수량은 필수 입력 값입니다.")
    private int quantity;

    public Order convertToEntity() {
        return Order.builder()
            .dealId(dealId)
            .userId(userId)
            .quantity(quantity)
            .build();
    }
}
