package com.flab.bbt.order.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Order {

    @Setter
    private Long id;
    private Long dealId;
    private Long userId;
    private int quantity;

    private DeliveryInfo deliveryInfo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
