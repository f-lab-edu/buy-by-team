package com.flab.bbt.payment.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Setter
    private long id;
    private long userId;
    private long orderId;
    private int method;
    private int status;

    public Payment updateStatus(PaymentStatus status) {
        this.status = status.statusCode;
        return this;
    }
}