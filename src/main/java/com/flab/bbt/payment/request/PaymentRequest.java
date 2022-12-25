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
    private long user_id;

    @NotNull(message = "Order ID는 필수 입력 값입니다.")
    private long order_id;

    @NotNull(message = "결제수단은 필수 입력 값입니다.")
    private int method;

    public Payment convertToEntity(){
        return Payment.builder()
            .user_id(user_id)
            .order_id(order_id)
            .method(method)
            .status(PaymentStatus.NEW.getStatusCode())
            .build();
    }
}
