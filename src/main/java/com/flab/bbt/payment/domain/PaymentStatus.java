package com.flab.bbt.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentStatus {
    // User/Auth
    SUCCESS(1, "결제가 성공적으로 완료되었습니다."),
    FAIL(2, "결제에 실패했습니다.");

    int statusCode;
    String message;

}


