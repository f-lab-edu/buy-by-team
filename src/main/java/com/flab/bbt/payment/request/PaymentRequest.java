package com.flab.bbt.payment.request;

import com.flab.bbt.payment.domain.Payment;
import com.flab.bbt.payment.domain.PaymentStatus;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentRequest {

    @NotNull(message = "User ID는 필수 입력 값입니다.")
    private long userId;

    @NotNull(message = "Order ID는 필수 입력 값입니다.")
    private long orderId;

    @NotNull(message = "결제수단은 필수 입력 값입니다.")
    private int method;

    public Payment convertToEntity() {
        return Payment.builder()
            .userId(userId)
            .orderId(orderId)
            .method(method)
            .status(PaymentStatus.NEW.getStatusCode())
            .build();
    }
}
