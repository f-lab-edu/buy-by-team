package com.flab.bbt.payment.domain;

import com.flab.bbt.user.domain.UserProfile;
import java.util.Optional;
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


    private long id;
    private long user_id;
    private long order_id;
    private int method;
    private int status;

    public Payment updateStatus(PaymentStatus status) {
        this.status = status.statusCode;
        return this;
    }
}
